package com.rakapermanaputra.popcorn.feature.detail.detail_tv

import androidx.appcompat.app.AppCompatActivity
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
import com.rakapermanaputra.popcorn.model.*
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.activity_detail_tv.*
import org.jetbrains.anko.design.snackbar

class DetailTvActivity : AppCompatActivity(), DetailTvContract.View {

    private lateinit var presenter: DetailTvPresenter

    private lateinit var sharedPreference: SharedPreference
    private var accountId: Int? = 0
    private var sessionId: String? = null
    private lateinit var reqFavBody: ReqFavBody
    private lateinit var reqWatchlistBody: ReqWatchlistBody
    private var isFavorite: Boolean = false
    private var isWatchlist: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)

        sharedPreference = SharedPreference(this)
        accountId = sharedPreference.getValueInt("ACCOUNT_ID")
        sessionId = sharedPreference.getValueString("SESSION_ID")

        val id = intent.getIntExtra("id", 0)
        val bundle = Bundle()
        bundle.putInt("id", id)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

        //request
        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = TvShowsRepoImpl(service)
        presenter = DetailTvPresenter(this, request)
        presenter.getDetail(id)
        if (sessionId != null) presenter.getTvState(id, sessionId!!)

        //fab listener
        fabFavorite.setOnClickListener {
            if (accountId != 0) {
                if (isFavorite == false) {
                    reqFavBody = ReqFavBody(true, id, "tv")
                    presenter.postFavorite(accountId!!, sessionId!!, reqFavBody)
                    isFavorite = true
                    it.snackbar("Added to favorite")
                    fabFavorite.colorNormal = resources.getColor(R.color.colorAccent)
                    fabFavorite.colorPressed = resources.getColor(R.color.colorAccent)
                } else {
                    reqFavBody = ReqFavBody(false, id, "tv")
                    presenter.postFavorite(accountId!!, sessionId!!, reqFavBody)
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
                    reqWatchlistBody = ReqWatchlistBody(id, "tv", true)
                    presenter.postWatchlist(accountId!!, sessionId!!, reqWatchlistBody)
                    isWatchlist = true
                    it.snackbar("Added to watchlist")
                    fabWatchlist.colorNormal = resources.getColor(R.color.colorAccent)
                    fabWatchlist.colorPressed = resources.getColor(R.color.colorAccent)
                } else {
                    reqWatchlistBody = ReqWatchlistBody(id, "tv", false)
                    presenter.postWatchlist(accountId!!, sessionId!!, reqWatchlistBody)
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

    override fun markFavorite(addResponse: AddResponse) {
        Log.d("Data", "status favorite : " + addResponse.statusMessage)

    }

    override fun markWatchlist(addResponse: AddResponse) {
        Log.d("Data", "status watchlist : " + addResponse.statusMessage)

    }

    override fun showTvState(stateFavorite: Boolean, stateWatchlist: Boolean) {
        isFavorite = stateFavorite

        if (isFavorite) fabFavorite.colorNormal = resources.getColor(R.color.colorAccent)

        Log.d("Favorite", "favorite state : $isFavorite")

        isWatchlist = stateWatchlist

        if (isWatchlist) fabFavorite.colorNormal = resources.getColor(R.color.colorAccent)

        Log.d("Favorite", "watchlist state : $isFavorite")
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
