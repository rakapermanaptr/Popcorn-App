package com.rakapermanaputra.popcorn.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.feature.detail.detail_movie.DetailActivity
import com.rakapermanaputra.popcorn.model.RecommendationMovie
import kotlinx.android.synthetic.main.item_poster.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class RecommendationAdapter(private val context: Context, private val recommendation: List<RecommendationMovie>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<RecommendationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        return RecommendationViewHolder(LayoutInflater.from(context).inflate(R.layout.item_poster, parent, false))
    }

    override fun getItemCount(): Int = recommendation.size

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bindItem(recommendation[position])
    }
}

class RecommendationViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(recommendationMovie: RecommendationMovie) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w185" + recommendationMovie.poster_path)
            .into(imgPoster)
        itemView.tvTitle.text = recommendationMovie.title

        itemView.setOnClickListener {
            itemView.context.startActivity<DetailActivity>("id" to recommendationMovie.id)
        }

    }
}