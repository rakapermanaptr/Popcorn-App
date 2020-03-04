package com.rakapermanaputra.popcorn.feature.detail.detail_people

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.ViewPagerAdapter
import com.rakapermanaputra.popcorn.feature.detail.detail_people.info.InfoPeopleFragment
import com.rakapermanaputra.popcorn.feature.detail.detail_people.movies.MoviesPeopleFragment
import com.rakapermanaputra.popcorn.feature.detail.detail_people.tvshows.TvshowsPeopleFragment
import com.rakapermanaputra.popcorn.model.PeopleDetail
import com.rakapermanaputra.popcorn.model.repository.PeopleRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.activity_detail_people.*

class DetailPeopleActivity : AppCompatActivity(), DetailPeopleContract.View {

    private lateinit var presenter: DetailPeoplePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_people)

        val id = intent.getIntExtra("id", 0)

        Log.d("data", "id : $id")

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = Bundle()
        bundle.putInt("id", id)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        val infoPeopleFragment = InfoPeopleFragment()
        val moviesPeopleFragment = MoviesPeopleFragment()
        val tvShowsPeopleFragment = TvshowsPeopleFragment()
        infoPeopleFragment.arguments = bundle
        moviesPeopleFragment.arguments = bundle
        tvShowsPeopleFragment.arguments = bundle
        adapter.populateFragment(infoPeopleFragment, "Info")
        adapter.populateFragment(moviesPeopleFragment, "Movies")
        adapter.populateFragment(tvShowsPeopleFragment, "Tv Shows")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = PeopleRepoImpl(service)
        presenter = DetailPeoplePresenter(this, request)
        presenter.getDetail(id)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showDetail(data: PeopleDetail) {
        supportActionBar?.title = data.name

        Glide.with(applicationContext)
            .load("http://image.tmdb.org/t/p/w500" + data.profile_path)
            .into(imgBackdrop)

        Glide.with(applicationContext)
            .load("http://image.tmdb.org/t/p/w500" + data.profile_path)
            .into(imgPoster)

        tvName.text = data.name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) this.finish()

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
