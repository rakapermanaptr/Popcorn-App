package com.rakapermanaputra.popcorn.feature.detail.detail_tv

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.ViewPagerAdapter
import com.rakapermanaputra.popcorn.db.SharedPreference
import com.rakapermanaputra.popcorn.feature.detail.detail_tv.actors.ActorsTvFragment
import com.rakapermanaputra.popcorn.feature.detail.detail_tv.info.InfoDetailTvFragment
import com.rakapermanaputra.popcorn.feature.detail.detail_tv.seasons.SeasonsTvFragment
import com.rakapermanaputra.popcorn.model.AddFavResponse
import com.rakapermanaputra.popcorn.model.DetailTv
import com.rakapermanaputra.popcorn.model.ReqFavBody
import com.rakapermanaputra.popcorn.model.TvShowsDetail
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.activity_detail_tv.*
import org.jetbrains.anko.act
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class DetailTvActivity : AppCompatActivity(), DetailTvContract.View {

    private lateinit var presenter: DetailTvPresenter

    private lateinit var sharedPreference: SharedPreference
    private var accountId: Int? = 0
    private  var sessionId: String? = null
    private lateinit var reqFavBody: ReqFavBody

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)

        val id = intent.getIntExtra("id", 0)

        sharedPreference = SharedPreference(this)
        accountId = sharedPreference?.getValueInt("ACCOUNT_ID")
        sessionId = sharedPreference?.getValueString("SESSION_ID")

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = Bundle()
        bundle.putInt("id", id)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        val infoDetailTvFragment = InfoDetailTvFragment()
        val actorsTvFragment = ActorsTvFragment()
        val seasonsTvFragment = SeasonsTvFragment()
        infoDetailTvFragment.arguments = bundle
        actorsTvFragment.arguments = bundle
        seasonsTvFragment.arguments = bundle
        adapter.populateFragment(infoDetailTvFragment, "Info")
        adapter.populateFragment(actorsTvFragment, "Actors")
        adapter.populateFragment(seasonsTvFragment, "Seasons")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = TvShowsRepoImpl(service)
        presenter = DetailTvPresenter(this, request)
        presenter.getDetail(id)

        //fab
        fab.setOnClickListener {
            if (accountId != 0) {
                reqFavBody = ReqFavBody(true, id, "tv")
                presenter.postFavTv(accountId!!, sessionId!!, reqFavBody)
                it.snackbar("Added to favorite")
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

    override fun showDetail(dataDetail: TvShowsDetail) {
        supportActionBar?.title = dataDetail.original_name

        Glide.with(applicationContext)
            .load("http://image.tmdb.org/t/p/w500" + dataDetail.backdrop_path)
            .into(imgBackdrop)

        Glide.with(applicationContext)
            .load("http://image.tmdb.org/t/p/w500" + dataDetail.poster_path)
            .into(imgPoster)

        tvYear.text = dataDetail.first_air_date.substring(0,4)
        tvTitle.text = dataDetail.original_name
    }

    override fun showMessage(addFavResponse: AddFavResponse) {
        Log.i("Data" , "Added to favorite message : " + addFavResponse.statusMessage)
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
