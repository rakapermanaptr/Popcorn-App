package com.rakapermanaputra.popcorn.feature.home.morepopular_discover

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.MoviesAdapter
import com.rakapermanaputra.popcorn.model.Movies
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.activity_more_discover.*

class MoreDiscoverActivity : AppCompatActivity(), MoreDiscoverContract.View {

    private lateinit var presenter: MoreDiscoverPresenter
    private var moreDiscoverMovies: MutableList<Movies> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_discover)

        supportActionBar?.title = "More Discover Movies"

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = MoviesRepoImpl(service)
        presenter = MoreDiscoverPresenter(this, request)
        presenter.getMoreDiscover()

    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showMoreDiscover(moreDiscover: List<Movies>) {
        moreDiscoverMovies.clear()
        moreDiscoverMovies.addAll(moreDiscover)
        val linearLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(
                applicationContext,
                LinearLayout.VERTICAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = MoviesAdapter(applicationContext, moreDiscoverMovies)
    }
}
