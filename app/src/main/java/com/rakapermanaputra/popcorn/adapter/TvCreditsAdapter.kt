package com.rakapermanaputra.popcorn.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
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

class TvCreditsAdapter(private val context: Context, private val movies: List<Credits>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<TvCreditsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvCreditsViewHolder {
        return TvCreditsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: TvCreditsViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }
}

class TvCreditsViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(movieCredits: Credits) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w185" + movieCredits.poster_path)
            .into(imgPoster)

        if (movieCredits.first_air_date.isEmpty()) {
            itemView.tvReleaseDate.text = "-"
        } else {
            itemView.tvReleaseDate.text = movieCredits.first_air_date.substring(0, 4)
        }

        if (movieCredits.character.isEmpty()) {
            itemView.tvActors.text = "-"
        } else {
            itemView.tvActors.text = "as " + movieCredits.character
        }

        itemView.tvTitle.text = movieCredits.original_name
        itemView.tvRate.text = movieCredits.vote_average.toString()

        Log.i("Data : ", "movie title : " + movieCredits.title)

        itemView.setOnClickListener {
            itemView.context.startActivity<DetailActivity>("id" to movieCredits.id)
        }

    }
}