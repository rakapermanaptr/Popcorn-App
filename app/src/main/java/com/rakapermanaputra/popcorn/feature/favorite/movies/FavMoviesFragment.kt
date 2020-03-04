package com.rakapermanaputra.popcorn.feature.favorite.movies


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.MoviesAdapter
import com.rakapermanaputra.popcorn.db.SharedPreference
import com.rakapermanaputra.popcorn.model.Movies
import com.rakapermanaputra.popcorn.model.repository.MoviesRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.fragment_fav_movies.*


class FavMoviesFragment : androidx.fragment.app.Fragment(), FavMoviesContract.View {

    private lateinit var presenter: FavMoviesPresenter
    private var favMovies: MutableList<Movies> = mutableListOf()

    private lateinit var sharedPreference: SharedPreference
    private var accountId: Int = 0
    private lateinit var sessionId: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreference = SharedPreference(requireContext())
        accountId = sharedPreference.getValueInt("ACCOUNT_ID")
        sessionId = sharedPreference.getValueString("SESSION_ID")!!

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = MoviesRepoImpl(service)
        presenter = FavMoviesPresenter(this, request)
        presenter.getFavoriteMovies(accountId, sessionId)

    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
    }

    override fun showFavMovie(movies: List<Movies>?) {
        favMovies.clear()
        if (movies != null) {
            favMovies.addAll(movies)
            val linearLayoutManager =
                LinearLayoutManager(
                    activity,
                    RecyclerView.VERTICAL,
                    false
                )
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = MoviesAdapter(requireContext(), favMovies)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoriteMovies(accountId, sessionId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movies, container, false)
    }


}
