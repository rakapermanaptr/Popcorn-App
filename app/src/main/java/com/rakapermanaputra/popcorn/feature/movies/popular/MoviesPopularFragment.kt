package com.rakapermanaputra.popcorn.feature.movies.popular


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.MoviesAdapter
import com.rakapermanaputra.popcorn.model.Movies
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.fragment_movie_popular.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MoviesPopularFragment : Fragment(),
    MoviesPopularContract.View {

    private lateinit var presenter: MoviesPopularPresenter
    private val popularMovies: MutableList<Movies> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = MoviesRepoImpl(service)
        presenter = MoviesPopularPresenter(this, request)
        presenter.getPopular()

    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showPopular(movies: List<Movies>) {
        popularMovies.clear()
        popularMovies.addAll(movies)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = MoviesAdapter(requireContext(), popularMovies)
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
        return inflater.inflate(R.layout.fragment_movie_popular, container, false)
    }


}
