package com.rakapermanaputra.popcorn.feature.home

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.model.Movies
import java.lang.Exception

class BannerAdapter(private val context: Context, private var bannerList: List<Movies>): PagerAdapter() {

    fun setBannerList(list: List<Movies>) {
        bannerList = list
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, o: Any): Boolean = view == o

    override fun getCount(): Int = bannerList.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        try {
            val movies: Movies = bannerList[position]
            val view = LayoutInflater.from(context).inflate(R.layout.item_home_nowplaying, null)
            val imagePoster = view.findViewById<ImageView>(R.id.imgBackdrop)
            val tvPosterName = view.findViewById<TextView>(R.id.tvTitle)
            Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500${movies.posterPath}")
                .into(imagePoster)
            tvPosterName.text = movies.title

            container.addView(view)
            return view
        } catch (e: Exception) { }
        return container
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}