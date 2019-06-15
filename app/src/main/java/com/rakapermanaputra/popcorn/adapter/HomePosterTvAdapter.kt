package com.rakapermanaputra.popcorn.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.DetailActivity
import com.rakapermanaputra.popcorn.feature.detail.detail_tv.DetailTvActivity
import com.rakapermanaputra.popcorn.model.Movies
import com.rakapermanaputra.popcorn.model.TvShows
import kotlinx.android.synthetic.main.item_home_poster.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class HomePosterTvAdapter(private val context: Context, private val tvShows: List<TvShows>) :
    RecyclerView.Adapter<HomePosterTvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePosterTvViewHolder {
        return HomePosterTvViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_poster, parent, false))
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: HomePosterTvViewHolder, position: Int) {
        holder.bindItem(tvShows[position])
    }
}

class HomePosterTvViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(tvshows: TvShows) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w500" + tvshows.posterPath)
            .into(imgPoster)

        itemView.tvTitle.text = tvshows.originalName

        itemView.setOnClickListener {
            val id = tvshows.id

            itemView.context.startActivity<DetailTvActivity>("id" to id)
        }

    }
}