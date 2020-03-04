package com.rakapermanaputra.popcorn.feature.movies.top_rated


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
import kotlinx.android.synthetic.main.fragment_movies_top_rated.*


class MoviesTopRatedFragment : androidx.fragment.app.Fragment(),
    MoviesTopRatedContract.View {

    private lateinit var presenter: MoviesTopRatedPresenter
    private var topRatedMovies: MutableList<Movies> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = MoviesRepoImpl(service)
        presenter = MoviesTopRatedPresenter(this, request)
        presenter.getTopRated()

    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showTopRated(movies: List<Movies>) {
        topRatedMovies.clear()
        topRatedMovies.addAll(movies)
        val linearLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(
                activity,
                LinearLayout.VERTICAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = MoviesAdapter(requireContext(), topRatedMovies)
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
        return inflater.inflate(R.layout.fragment_movies_top_rated, container, false)
    }


}
