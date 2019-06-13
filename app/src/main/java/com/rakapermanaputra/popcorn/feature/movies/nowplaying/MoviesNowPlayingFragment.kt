package com.rakapermanaputra.popcorn.feature.movies.nowplaying


import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.AdapterTheMovieDb
import com.rakapermanaputra.popcorn.adapter.MoviesAdapter
import com.rakapermanaputra.popcorn.model.Movies
import com.rakapermanaputra.popcorn.model.MoviesResponse
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie_now_playing.*
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MoviesNowPlayingFragment : Fragment(), MoviesNowPlayingContract.View {

    private lateinit var presenter: MoviesNowPlayingPresenter
    private var nowPlayingMovies: MutableList<Movies> = mutableListOf()

    //
//    private var adapterTheMovieDb by Delegates.notNull<AdapterTheMovieDb>()
//    private var isLoading by Delegates.notNull<Boolean>()
//    private var page by Delegates.notNull<Int>()
//    private var totalPage by Delegates.notNull<Int>()
    //

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        page = 1
//        totalPage = 0
//        doLoadData()
//        initListener()

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = MoviesRepoImpl(service)
        presenter = MoviesNowPlayingPresenter(this, request)
        presenter.getNowPlaying()
//        presenter.getNowPlaying(page = page)

    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showNowPlaying(movies: List<Movies>) {
        nowPlayingMovies.clear()
        nowPlayingMovies.addAll(movies)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = MoviesAdapter(requireContext(), nowPlayingMovies)
    }

//    private fun doLoadData() {
//        showLoading(true)
//
//        val service = ApiService.getClient().create(ApiRest::class.java)
//        service.getNowPlayingMovies(page = page)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(
//                {
//                    movies: MoviesResponse ->
//                    val resultTheMovieDb = movies.results as ArrayList
//                    if (page == 1) {
//                        adapterTheMovieDb = AdapterTheMovieDb(requireContext(), resultTheMovieDb)
//                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//                        recyclerView.adapter = adapterTheMovieDb
//                    } else {
//                        adapterTheMovieDb.refreshAdapter(resultTheMovieDb)
//                    }
//                    totalPage = movies.totalPages
//                },
//                {
//                    t: Throwable? -> t?.printStackTrace()
//                },
//                {
//                    hideLoading()
//                }
//            )
//    }
//
//    private fun initListener() {
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
//                val countItem = linearLayoutManager.itemCount
//                val lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
//                val isLastPosition = countItem.minus(1) == lastVisiblePosition
//                Log.d(TAG, "countItem: $countItem")
//                Log.d(TAG, "lastVisiblePosition: $lastVisiblePosition")
//                Log.d(TAG, "isLastPosition: $isLastPosition")
//                if (!isLoading && isLastPosition && page < totalPage) {
//                    showLoading(true)
//                    page = page.let { it.plus(1) }
//                    doLoadData()
//                }
//            }
//        })
//    }
//
//
//    private fun showLoading(isRefresh: Boolean) {
//        isLoading = true
//        loadingAnim.visibility = View.INVISIBLE
//        recyclerView.visibility.let {
//            if (isRefresh) {
//                View.VISIBLE
//            } else {
//                View.GONE
//            }
//        }
//    }

//    private fun hideLoading() {
//        isLoading = false
//        loadingAnim.visibility = View.GONE
//        recyclerView.visibility = View.VISIBLE
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_now_playing, container, false)
    }


}
