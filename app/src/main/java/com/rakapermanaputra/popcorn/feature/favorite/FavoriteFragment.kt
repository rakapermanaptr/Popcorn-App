package com.rakapermanaputra.popcorn.feature.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.ViewPagerAdapter
import com.rakapermanaputra.popcorn.feature.favorite.movies.FavMoviesFragment
import com.rakapermanaputra.popcorn.feature.favorite.tv.FavTvFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)
        val favMoviesFragment = FavMoviesFragment()
        val favTvFragment = FavTvFragment()
        adapter.populateFragment(favMoviesFragment, "Movies")
        adapter.populateFragment(favTvFragment, "Tv Shows")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }


}
