package com.rakapermanaputra.popcorn.feature.favorite.tv


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.TvshowsAdapter
import com.rakapermanaputra.popcorn.db.SharedPreference
import com.rakapermanaputra.popcorn.model.TvShows
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.fragment_fav_tv.*
import org.jetbrains.anko.support.v4.toast


class FavTvFragment : androidx.fragment.app.Fragment(), FavTvContract.View {

    private lateinit var presenter: FavTvPresenter
    private var favTv: MutableList<TvShows> = mutableListOf()

    private lateinit var sharedPreference: SharedPreference
    private var accountId: Int = 0
    private lateinit var sessionId : String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreference = SharedPreference(requireContext())
        accountId = sharedPreference.getValueInt("ACCOUNT_ID")
        sessionId = sharedPreference.getValueString("SESSION_ID")!!

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = TvShowsRepoImpl(service)
        presenter = FavTvPresenter(this, request)
        presenter.getFavoriteTv(accountId, sessionId)

    }

    override fun showLoading() {
        recyclerView.invisible()
        loadingAnim.visible()
    }

    override fun hideLoading() {
        recyclerView.visible()
        loadingAnim.invisible()
    }

    override fun showFavTv(tvShows: List<TvShows>?) {
        favTv.clear()
        if (tvShows != null) {
            favTv.addAll(tvShows)
            val linearLayoutManager =
                LinearLayoutManager(
                    activity,
                    RecyclerView.VERTICAL,
                    false
                )
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = TvshowsAdapter(requireContext(), favTv)
        } else {
            toast("There is no favorite tv shows")
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavoriteTv(accountId, sessionId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv, container, false)
    }


}
