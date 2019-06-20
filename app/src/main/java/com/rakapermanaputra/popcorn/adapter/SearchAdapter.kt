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
import com.rakapermanaputra.popcorn.model.Search
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class SearchAdapter(private val context: Context, private val search: List<Search>) :
    RecyclerView.Adapter<SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = search.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindItem(search[position])
    }
}

class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(search: Search) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w185" + search.posterPath)
            .into(imgPoster)

        if (search.mediaType == "tv") {
            itemView.tvTitle.text = search.name
            itemView.tvReleaseDate.text = search.firstAirDate?.substring(0,4)
            itemView.tvRate.text = search.voteAverage.toString()
        } else {
            itemView.tvTitle.text = search.title
            itemView.tvReleaseDate.text = search.release_date?.substring(0,4)
            itemView.tvRate.text = search.voteAverage.toString()
        }



//        itemView.tvRate.text = movies.voteAverage.toString()

//        Log.i("Data : " , "movie title : " + movies.title)
//
//        itemView.setOnClickListener {
//            val id = movies.id
//
//            val intent = Intent(itemView.context, DetailActivity::class.java)
//            intent.putExtra("id", id)
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            itemView.context.startActivity(intent)
//        }

    }
}