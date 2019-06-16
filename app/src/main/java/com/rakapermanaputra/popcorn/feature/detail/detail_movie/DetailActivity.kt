package com.rakapermanaputra.popcorn.feature.detail.detail_movie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.rakapermanaputra.popcorn.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import com.bumptech.glide.Glide
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.db.SharedPreference
import com.rakapermanaputra.popcorn.db.favorite.Favorite
import com.rakapermanaputra.popcorn.db.favorite.database
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.Info.InfoDetailFragment
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.cast.CastMovieFragment
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.reviews.ReviewsMovieFragment
import com.rakapermanaputra.popcorn.model.AddFavResponse
import com.rakapermanaputra.popcorn.model.DetailMovie
import com.rakapermanaputra.popcorn.model.ReqFavBody
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import com.rakapermanaputra.popcorn.model.repository.LocalRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.share
import org.jetbrains.anko.toast


class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var presenter: DetailMoviePresenter

    private var id: Int = 0

    private lateinit var sharedPreference: SharedPreference
    private var sessionId: String? = null
    private var accountId: Int? = null
    private lateinit var reqFavBody: ReqFavBody

    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        sharedPreference = SharedPreference(this)
        sessionId = sharedPreference?.getValueString("SESSION_ID")
        accountId = sharedPreference?.getValueInt("ACCOUNT_ID")

        id = intent.getIntExtra("id", 0)

        val bundle = Bundle()
        bundle.putInt("id", id)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        val infoDetailFragment = InfoDetailFragment()
        val castMovieFragment = CastMovieFragment()
        val reviewsMovieFragment = ReviewsMovieFragment()
        infoDetailFragment.arguments = bundle
        castMovieFragment.arguments = bundle
        reviewsMovieFragment.arguments = bundle
        adapter.populateFragment(infoDetailFragment, "Info")
        adapter.populateFragment(castMovieFragment, "Cast")
        adapter.populateFragment(reviewsMovieFragment, "Reviews")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = DetailMovieRepoImpl(service)
        val localRepoImpl = LocalRepoImpl(applicationContext)
        presenter = DetailMoviePresenter(this, request, localRepoImpl)
        presenter.getDetail(id)

        favoriteState()
        setFavorite()

        fab.setOnClickListener {
            if (accountId != 0) {
                if (!isFavorite) {
                    reqFavBody = ReqFavBody(true, id, "movie")
                    presenter.postFavMovie(accountId!!, sessionId!!, reqFavBody)
                    presenter.insertFavorite(id)
                    it.snackbar("Added to favorite")

                    isFavorite = !isFavorite
                } else {
                    presenter.deleteFavorite(id)
                    it.snackbar("Removed from favorite")
                    isFavorite = !isFavorite
                }
            } else {
                it.snackbar("You must login first")
            }
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showBackdrop(detailMovie: DetailMovie) {
        Glide.with(applicationContext)
            .load("http://image.tmdb.org/t/p/w500" + detailMovie.backdrop_path)
            .into(imgBackdrop)
    }

    override fun showPoster(detailMovie: DetailMovie) {
        Glide.with(applicationContext)
            .load("http://image.tmdb.org/t/p/w500" + detailMovie.poster_path)
            .into(imgPoster)
    }

    override fun showDetail(detailMovie: DetailMovie) {
        supportActionBar?.title = detailMovie.title
        tvTitle.text = detailMovie.title
        tvYear.text = detailMovie.release_date.substring(0, 4)

        for (genre in detailMovie.genres) {
            tvGenres.append("${genre.name}, ")
        }
    }

    override fun showMessage(addFavResponse: AddFavResponse) {
        Log.d("Data", "status favorite : " + addFavResponse.statusMessage)

        fab.setImageResource(R.drawable.ic_favorite_white_24dp)
    }

    override fun setFavoriteState(favList: List<Favorite>) {
        if (!favList.isEmpty()) isFavorite = true
    }

    private fun setFavorite() {
        if (isFavorite)
            fab.setImageResource(R.drawable.ic_favorite_white_24dp)
        else
            fab.setImageResource(R.drawable.ic_favorite_border_white_24dp)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("MOVIE_ID = {id}",
                    "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) this.finish()

        return super.onOptionsItemSelected(item)
    }

}
