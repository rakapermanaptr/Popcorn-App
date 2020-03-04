package com.rakapermanaputra.popcorn.feature.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.model.Movies

class HomeItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val posterTitle = itemView.findViewById(R.id.tvTitle) as TextView
    private val posterImage = itemView.findViewById(R.id.imgPoster) as ImageView

    fun bindItem(movies: Movies) {
        posterTitle.text = movies.title

        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w500${movies.posterPath}")
            .into(posterImage)
    }

}