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
import com.rakapermanaputra.popcorn.model.Movies
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_home_poster.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomePosterMovieAdapter(private val context: Context, private val movies: List<Movies>) :
    RecyclerView.Adapter<HomePosterMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePosterMovieViewHolder {
        return HomePosterMovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_poster, parent, false))
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: HomePosterMovieViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }
}

class HomePosterMovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(movies: Movies) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w500" + movies.posterPath)
            .into(imgPoster)
        itemView.tvTitle.text = movies.title

        itemView.setOnClickListener {
            val id = movies.id

            itemView.context.startActivity<DetailActivity>("id" to id)
        }

    }
}