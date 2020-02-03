package com.rakapermanaputra.popcorn.feature.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.rakapermanaputra.popcorn.feature.home.morepopular_movie.MorePopularActivity

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.*
import com.rakapermanaputra.popcorn.feature.home.morepopular_discover.MoreDiscoverActivity
import com.rakapermanaputra.popcorn.feature.home.morepopular_tv.MorePopularTvActivity
import com.rakapermanaputra.popcorn.model.*
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.padding
import org.jetbrains.anko.support.v4.startActivity


class HomeFragment : Fragment(), HomeFragmentContract.View {

    private var nowPlayingMovies: MutableList<Movies> = mutableListOf()
    private val popularMovie: MutableList<Movies> = mutableListOf()
    private val popularTv: MutableList<TvShows> = mutableListOf()
    private val discoverMovie: MutableList<Movies> = mutableListOf()

    private lateinit var presenter: HomeFragmentPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val service = ApiService.getClient().create(ApiRest::class.java)
        val requestMovie = MoviesRepoImpl(service)
        val requestTv = TvShowsRepoImpl(service)
        presenter = HomeFragmentPresenter(this, requestMovie, requestTv)

        presenter.getNowPlayingMovies()
        presenter.getPopularMovies()
        presenter.getPopularTvs()
        presenter.getDiscoverMovies()

        //onclick-more
        ic_more_popular_movie.setOnClickListener {
            startActivity<MorePopularActivity>()
        }

        ic_more_popular_tv.setOnClickListener {
            startActivity<MorePopularTvActivity>()
        }

        ic_more_discover_movie.setOnClickListener {
            startActivity<MoreDiscoverActivity>()
        }

    }

    override fun showNowPlayingMovies(npMovies: List<Movies>) {
        nowPlayingMovies.clear()
        nowPlayingMovies.addAll(npMovies)
        val bannerAdapter = BannerAdapter(requireContext(), nowPlayingMovies)
        viewPagerBanner.clipToPadding = false
        viewPagerBanner.padding = 16
        viewPagerBanner.pageMargin = 8
        viewPagerBanner.adapter = bannerAdapter
    }

    override fun showPopularMovies(popularMovies: List<Movies>) {
        popularMovie.clear()
        popularMovie.addAll(popularMovies)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        rvPopularMovie.layoutManager = linearLayoutManager
        rvPopularMovie.adapter = HomePosterMovieAdapter(requireContext(), popularMovie)
    }

    override fun showPopularTvs(popularTvs: List<TvShows>) {
        popularTv.clear()
        popularTv.addAll(popularTvs)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        rvPopularTv.layoutManager = linearLayoutManager
        rvPopularTv.adapter = HomePosterTvAdapter(requireContext(), popularTv)
    }

    override fun showDiscoverMovies(discoverMovies: List<Movies>) {
        discoverMovie.clear()
        discoverMovie.addAll(discoverMovies)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        rvDiscoverMovie.layoutManager = linearLayoutManager
        rvDiscoverMovie.adapter = HomePosterMovieAdapter(requireContext(), discoverMovie)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}
