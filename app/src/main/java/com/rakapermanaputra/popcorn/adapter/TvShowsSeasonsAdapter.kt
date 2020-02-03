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
import com.rakapermanaputra.popcorn.feature.detail.detail_tv.DetailTvActivity
import com.rakapermanaputra.popcorn.model.Movies
import com.rakapermanaputra.popcorn.model.Season
import com.rakapermanaputra.popcorn.model.TvShows
import com.rakapermanaputra.popcorn.utils.invisible
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class TvShowsSeasonsAdapter(private val context: Context, private val season: List<Season>) :
    RecyclerView.Adapter<TvSeasonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeasonsViewHolder {
        return TvSeasonsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = season.size

    override fun onBindViewHolder(holder: TvSeasonsViewHolder, position: Int) {
        holder.bindItem(season[position])
    }
}

class TvSeasonsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(season: Season) {
        itemView.imgTmdbLogo.invisible()
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w185" + season.poster_path)
            .into(imgPoster)
        itemView.tvReleaseDate.text = season.air_date.substring(0, 4)
        itemView.tvTitle.text = season.name

        itemView.setOnClickListener {
            val id = season.id

            itemView.context.toast("tv season id: " + id + " upcoming")
//            itemView.context.startActivity<DetailTvActivity>("id" to id)
        }

    }
}