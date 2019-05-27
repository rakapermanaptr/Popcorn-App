package com.rakapermanaputra.popcorn.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.DetailActivity
import com.rakapermanaputra.popcorn.model.Movies
import kotlinx.android.synthetic.main.item_home_nowplaying.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class HomeNowPlayingAdapter(private val context: Context, private val movies: List<Movies>) :
    RecyclerView.Adapter<HomeNowPlayingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNowPlayingViewHolder {
        return HomeNowPlayingViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_nowplaying, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: HomeNowPlayingViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }
}

class HomeNowPlayingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgBackdrop = view.find<ImageView>(R.id.imgBackdrop)

    fun bindItem(movies: Movies) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w500" + movies.backdropPath)
            .into(imgBackdrop)

        itemView.tvTitle.text = movies.title

        itemView.setOnClickListener {
            val id = movies.id

            itemView.context.startActivity<DetailActivity>("id" to id)
        }

    }
}