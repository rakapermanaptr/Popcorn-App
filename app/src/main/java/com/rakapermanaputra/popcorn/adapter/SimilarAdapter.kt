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
import com.rakapermanaputra.popcorn.model.SimilarMovie
import kotlinx.android.synthetic.main.item_poster.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class SimilarAdapter(private val context: Context, private val similar: List<SimilarMovie>) :
    RecyclerView.Adapter<PosterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        return PosterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_poster, parent, false))
    }

    override fun getItemCount(): Int = similar.size

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        holder.bindItem(similar[position])
    }
}

class PosterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(similarMovie: SimilarMovie) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w185" + similarMovie.poster_path)
            .into(imgPoster)
        itemView.tvTitle.text = similarMovie.title

        itemView.setOnClickListener {
            val id = similarMovie.id

            itemView.context.startActivity<DetailActivity>("id" to id)
        }

    }
}