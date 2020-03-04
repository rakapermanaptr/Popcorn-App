package com.rakapermanaputra.popcorn.feature.detail.detail_tv.seasons


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.TvShowsSeasonsAdapter
import com.rakapermanaputra.popcorn.model.Season
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.fragment_seasons.*


class SeasonsTvFragment : androidx.fragment.app.Fragment(), SeasonsTvContract.View {

    private lateinit var presenter: SeasonsTvPresenter
    private var tvSeasons: MutableList<Season> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = arguments!!.getInt("id")

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = TvShowsRepoImpl(service)
        presenter = SeasonsTvPresenter(this, request)
        presenter.getTvSeasons(id)


        Log.i("Data", "id seasons : $id")
    }

    override fun showSeasons(season: List<Season>) {
        tvSeasons.clear()
        tvSeasons.addAll(season)
        val linearLayoutManager =
            LinearLayoutManager(
                activity,
                RecyclerView.VERTICAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = TvShowsSeasonsAdapter(requireContext(), tvSeasons)
    }

    override fun showLoading() {
        loadingAnim.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        loadingAnim.invisible()
        recyclerView.visible()
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
        return inflater.inflate(R.layout.fragment_seasons, container, false)
    }


}
