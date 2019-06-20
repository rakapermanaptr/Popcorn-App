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
import com.rakapermanaputra.popcorn.model.Credits
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MovieCreditsAdapter(private val context: Context, private val movies: List<Credits>) :
    RecyclerView.Adapter<MovieCreditsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCreditsViewHolder {
        return MovieCreditsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieCreditsViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }
}

class MovieCreditsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(movieCredits: Credits) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w185" + movieCredits.poster_path)
            .into(imgPoster)

        itemView.tvReleaseDate.text = movieCredits.release_date?.substring(0, 4)

        if (movieCredits.character.isEmpty()) {
            itemView.tvActors.text = "-"
        } else {
            itemView.tvActors.text = "as " + movieCredits.character
        }

        itemView.tvTitle.text = movieCredits.title
        itemView.tvRate.text = movieCredits.vote_average.toString()

        Log.i("Data : " , "movie title : " + movieCredits.title)

        itemView.setOnClickListener {
            itemView.context.startActivity<DetailActivity>("id" to movieCredits.id)
        }

    }
}