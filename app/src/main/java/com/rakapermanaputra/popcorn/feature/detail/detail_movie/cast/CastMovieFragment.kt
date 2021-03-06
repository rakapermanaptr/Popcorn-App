package com.rakapermanaputra.popcorn.feature.detail.detail_movie.cast


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.CasterAdapter
import com.rakapermanaputra.popcorn.model.Cast
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.fragment_cast_detail.*

class CastMovieFragment : androidx.fragment.app.Fragment(), CastMovieContract.View {

    private var casterMovie: MutableList<Cast> = mutableListOf()
    private lateinit var presenter: CastMoviePresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = arguments!!.getInt("id")

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = DetailMovieRepoImpl(service)
        presenter = CastMoviePresenter(this, request)
        presenter.getCaster(id)
    }

    override fun showLoading() {
        progressBar.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun showCaster(caster: List<Cast>) {
        casterMovie.clear()
        casterMovie.addAll(caster)
        val linearLayoutManager =
            LinearLayoutManager(
                activity,
                RecyclerView.VERTICAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = CasterAdapter(requireContext(), casterMovie)
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
        return inflater.inflate(R.layout.fragment_cast_detail, container, false)
    }


}
