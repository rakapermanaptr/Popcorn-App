package com.rakapermanaputra.popcorn.feature.detail.detail_movie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.rakapermanaputra.popcorn.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import com.bumptech.glide.Glide
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.Info.InfoDetailFragment
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.cast.CastMovieFragment
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.reviews.ReviewsMovieFragment
import com.rakapermanaputra.popcorn.model.DetailMovie
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible


class DetailActivity : AppCompatActivity(),
    DetailContract.View {

    private lateinit var presenter: DetailMoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra("id", 0)

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
        presenter = DetailMoviePresenter(this, request)
        presenter.getDetail(id)

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

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
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
