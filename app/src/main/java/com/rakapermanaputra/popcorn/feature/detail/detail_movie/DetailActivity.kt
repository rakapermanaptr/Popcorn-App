package com.rakapermanaputra.popcorn.feature.detail.detail_movie

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.rakapermanaputra.popcorn.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import com.bumptech.glide.Glide
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.db.SharedPreference
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.Info.InfoDetailFragment
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.cast.CastMovieFragment
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.reviews.ReviewsMovieFragment
import com.rakapermanaputra.popcorn.model.AddResponse
import com.rakapermanaputra.popcorn.model.DetailMovie
import com.rakapermanaputra.popcorn.model.ReqFavBody
import com.rakapermanaputra.popcorn.model.ReqWatchlistBody
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast


class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var presenter: DetailMoviePresenter

    private var id: Int = 0

    private lateinit var sharedPreference: SharedPreference
    private var sessionId: String? = null
    private var accountId: Int? = null
    private lateinit var reqFavBody: ReqFavBody
    private lateinit var reqWatchlistBody: ReqWatchlistBody
    private var isFavorite: Boolean = false
    private var isWatchlist: Boolean = false

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

        //request
        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = DetailMovieRepoImpl(service)
        presenter = DetailMoviePresenter(this, request)
        presenter.getDetail(id)
        if (sessionId != null) {
            presenter.getMovieState(id, sessionId!!)
        }

        //fab favorite listener
        fabFavorite.setOnClickListener {
            if (accountId != 0) {
                if (isFavorite == false) {
                    reqFavBody = ReqFavBody(true, id, "movie")
                    presenter.postFavMovie(accountId!!, sessionId!!, reqFavBody)
                    isFavorite = true
                    it.snackbar("Added to favorite")
                    fabFavorite.colorNormal = resources.getColor(R.color.colorAccent)
                    fabFavorite.colorPressed = resources.getColor(R.color.colorAccent)
                } else {
                    reqFavBody = ReqFavBody(false, id, "movie")
                    presenter.postFavMovie(accountId!!, sessionId!!, reqFavBody)
                    isFavorite = false
                    it.snackbar("Removed from favorite")
                    fabFavorite.colorNormal = resources.getColor(R.color.colorWhite)
                    fabFavorite.colorPressed = resources.getColor(R.color.colorWhite)
                }
            } else {
                it.snackbar("You must login first")
            }
        }

        //fab watchlist
        fabWatchlist.setOnClickListener {
            if (accountId != 0) {
                if (isWatchlist == false) {
                    reqWatchlistBody = ReqWatchlistBody(id, "movie", true)
                    presenter.postWatchlistMovie(accountId!!, sessionId!!, reqWatchlistBody)
                    isWatchlist = true
                    it.snackbar("Added to watchlist")
                    fabWatchlist.colorNormal = resources.getColor(R.color.colorAccent)
                    fabWatchlist.colorPressed = resources.getColor(R.color.colorAccent)
                } else {
                    reqWatchlistBody = ReqWatchlistBody(id, "movie", false)
                    presenter.postWatchlistMovie(accountId!!, sessionId!!, reqWatchlistBody)
                    isWatchlist = false
                    it.snackbar("Removed from watchlist")
                    fabWatchlist.colorNormal = resources.getColor(R.color.colorWhite)
                    fabWatchlist.colorPressed = resources.getColor(R.color.colorWhite)
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

    override fun markFavorite(addResponse: AddResponse) {
        Log.d("Data", "status favorite : " + addResponse.statusMessage)

    }

    override fun markWatchlist(addResponse: AddResponse) {
        Log.d("Data", "status watchlist : " + addResponse.statusMessage)
    }

    override fun showFavoriteState(state: Boolean) {
        isFavorite = state

        if (isFavorite == true) fabFavorite.colorNormal = resources.getColor(R.color.colorAccent)
        
        Log.d("Favorite", "favorite state : " + isFavorite)
    }

    override fun showWatchlistState(state: Boolean) {
        isWatchlist = state

        if (isWatchlist == true) fabWatchlist.colorNormal = resources.getColor(R.color.colorAccent)

        Log.d("Watchlist", "watchlist state : " + isWatchlist)
    }


    override fun onResume() {
        super.onResume()
        Log.i("Data", "State movie : resume")
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
