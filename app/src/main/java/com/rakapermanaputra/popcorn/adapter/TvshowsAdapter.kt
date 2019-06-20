package com.rakapermanaputra.popcorn.adapter

import android.content.Context
import android.content.Intent
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
import com.rakapermanaputra.popcorn.utils.invisible
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class TvshowsAdapter(private val context: Context, private val tvShows: List<TvShows>) :
    RecyclerView.Adapter<TvshowsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvshowsViewHolder {
        return TvshowsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: TvshowsViewHolder, position: Int) {
        holder.bindItem(tvShows[position])
    }
}

class TvshowsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(tvShows: TvShows) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w185" + tvShows.posterPath)
            .into(imgPoster)
        itemView.tvReleaseDate.text = tvShows.firstAirDate?.substring(0,4)
        itemView.tvTitle.text = tvShows.name
        itemView.tvRate.text = tvShows.voteAverage.toString()

        itemView.setOnClickListener {
            itemView.context.startActivity<DetailActivity>("id" to tvShows.id)

        }

    }
}