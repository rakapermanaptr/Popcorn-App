package com.rakapermanaputra.popcorn.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.model.Movies
import kotlinx.android.synthetic.main.list_item.view.*

class AdapterTheMovieDb(private val context: Context, private var result: ArrayList<Movies>)
    : androidx.recyclerview.widget.RecyclerView.Adapter<AdapterTheMovieDb.ViewHolder>() {

    private val TAG: String = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultItem = result[position]

        Glide.with(context)
            .load("http://image.tmdb.org/t/p/w185" + resultItem.posterPath)
            .into(holder.itemView.imgPoster)

        holder.itemView.tvReleaseDate.text = resultItem.releaseDate
        holder.itemView.tvTitle.text = resultItem.title
        holder.itemView.tvRate.text = resultItem.voteAverage.toString()
    }

    fun refreshAdapter(result: List<Movies>) {
        this.result.addAll(result)
        notifyItemRangeChanged(0, this.result.size)
    }

    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)
}