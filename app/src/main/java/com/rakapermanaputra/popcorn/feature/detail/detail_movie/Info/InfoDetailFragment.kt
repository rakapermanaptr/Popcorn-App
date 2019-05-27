package com.rakapermanaputra.popcorn.feature.detail.detail_movie.Info


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.RecommendationAdapter
import com.rakapermanaputra.popcorn.adapter.SimilarAdapter
import com.rakapermanaputra.popcorn.model.*
import com.rakapermanaputra.popcorn.model.repository.DetailMovieRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.fragment_info_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class InfoDetailFragment : Fragment(), InfoDetailContract.View {

    private lateinit var presenter: InfoDetailPresenter
    private var similarMovies: MutableList<SimilarMovie> = mutableListOf()
    private var recommendationMovie: MutableList<RecommendationMovie> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = arguments!!.getInt("id")

        imgTmdbLogo.invisible()

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = DetailMovieRepoImpl(service)
        presenter = InfoDetailPresenter(this, request)
        presenter.getDetail(id)
        presenter.getSimilarMovies(id)
        presenter.getRecommendationMovies(id)

    }

    override fun showDetail(detail: DetailMovie) {
        tvRate.text = detail.vote_average.toString()
        tvOverview.text = detail.overview
        tvOverview.setOnClickListener { tvOverview.maxLines = 99 }
        tvOriginalTitle.text = detail.original_title
        tvOriginalLanguage.text = detail.original_language
        tvBudget.text = detail.budget.toString()
        tvRevenue.text = detail.revenue.toString()
        tvHomePage.text = detail.homepage

        imgTmdbLogo.visible()
    }

    override fun showSimilarMovies(similar: List<SimilarMovie>) {
        similarMovies.clear()
        similarMovies.addAll(similar)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        rvSimilar.layoutManager = linearLayoutManager
        rvSimilar.adapter = SimilarAdapter(requireContext(), similarMovies)
    }

    override fun showRecommendation(recommend: List<RecommendationMovie>) {
        recommendationMovie.clear()
        recommendationMovie.addAll(recommend)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvRecommendation.layoutManager = linearLayoutManager
        rvRecommendation.adapter = RecommendationAdapter(requireContext(), recommendationMovie)
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
        return inflater.inflate(R.layout.fragment_info_detail, container, false)
    }


}
