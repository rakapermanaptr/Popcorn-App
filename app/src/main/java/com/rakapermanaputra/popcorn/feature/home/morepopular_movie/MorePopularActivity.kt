package com.rakapermanaputra.popcorn.feature.home.morepopular_movie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.MoviesAdapter
import com.rakapermanaputra.popcorn.model.Movies
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.activity_more_popular.*
import org.jetbrains.anko.act

class MorePopularActivity : AppCompatActivity(),
    MoreMovieContract.View {

    private lateinit var presenter: MoreMoviePresenter
    private var morePopularMovies: MutableList<Movies> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_popular)

        supportActionBar?.title = "Popular Movies"

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = MoviesRepoImpl(service)
        presenter = MoreMoviePresenter(this, request)
        presenter.getMorePopularMovie()

    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showMorePopularMovie(morePopular: List<Movies>) {
        morePopularMovies.clear()
        morePopularMovies.addAll(morePopular)
        val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = MoviesAdapter(applicationContext, morePopularMovies)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
