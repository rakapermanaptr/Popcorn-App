package com.rakapermanaputra.popcorn.feature.detail.detail_tv.info


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.model.CreatedBy
import com.rakapermanaputra.popcorn.model.TvShowsDetail
import com.rakapermanaputra.popcorn.model.repository.TvShowsRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import kotlinx.android.synthetic.main.fragment_info.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class InfoDetailTvFragment : Fragment(), InfoDetailTvContract.View {

    private lateinit var presenter: InfoDetailPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = arguments!!.getInt("id")

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = TvShowsRepoImpl(service)
        presenter = InfoDetailPresenter(this, request)
        presenter.getInfoDetail(id)

        Log.i("Data", "id : " + id)
    }

    override fun showInfoDetail(dataDetail: TvShowsDetail) {
        tvRate.text = dataDetail.vote_average.toString()
        tvOverview.text = dataDetail.overview
        tvOverview.setOnClickListener { tvOverview.maxLines = 99 }
        tvOriginalTitle.text = dataDetail.name
        tvShowType.text = dataDetail.type
        tvShowStatus.text = dataDetail.status
        tvFirstAirDate.text = dataDetail.first_air_date
        tvLastAirDate.text = dataDetail.last_air_date
        for (creator in dataDetail.created_by) {
            tvCreatedBy.append("${creator.name}")
        }
        tvHomePage.text = dataDetail.homepage
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
        return inflater.inflate(R.layout.fragment_info, container, false)
    }


}
