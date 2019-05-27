package com.rakapermanaputra.popcorn.adapter

import android.content.Context
import android.content.Intent
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
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MoviesAdapter(private val context: Context, private val movies: List<Movies>) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(movies: Movies) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w185" + movies.posterPath)
            .into(imgPoster)
        itemView.tvReleaseDate.text = movies.releaseDate?.substring(0,4)
        itemView.tvTitle.text = movies.title
        itemView.tvRate.text = movies.voteAverage.toString()

        Log.i("Data : " , "movie title : " + movies.title)

        itemView.setOnClickListener {
            val id = movies.id

            val intent = Intent(itemView.context, DetailActivity::class.java)
            intent.putExtra("id", id)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            itemView.context.startActivity(intent)
        }

    }
}