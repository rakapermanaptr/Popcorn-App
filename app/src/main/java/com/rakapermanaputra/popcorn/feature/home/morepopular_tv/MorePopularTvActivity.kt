package com.rakapermanaputra.popcorn.feature.home.morepopular_tv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.TvshowsAdapter
import com.rakapermanaputra.popcorn.model.TvShows
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.activity_more_popular_tv.*

class MorePopularTvActivity : AppCompatActivity(),
    MoreTvContract.View {

    private lateinit var presenter: MoreTvPresenter
    private var morePopularTv: MutableList<TvShows> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_popular_tv)

        supportActionBar?.title = "More Popular Tv"

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = TvShowsRepoImpl(service)
        presenter = MoreTvPresenter(this, request)
        presenter.getMorePopularTv()
    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showMorePopularTv(moreTv: List<TvShows>) {
        morePopularTv.clear()
        morePopularTv.addAll(moreTv)
        val linearLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(
                applicationContext,
                LinearLayout.VERTICAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = TvshowsAdapter(applicationContext, morePopularTv)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
