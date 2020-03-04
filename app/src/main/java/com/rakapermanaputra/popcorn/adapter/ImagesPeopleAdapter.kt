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
import com.rakapermanaputra.popcorn.model.ImagesPeople
import com.rakapermanaputra.popcorn.utils.invisible
import kotlinx.android.synthetic.main.item_poster.view.*
import org.jetbrains.anko.find

class ImagesPeopleAdapter(private val context: Context, private val images: List<ImagesPeople>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ImagesPeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesPeopleViewHolder {
        return ImagesPeopleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_poster, parent, false))
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImagesPeopleViewHolder, position: Int) {
        holder.bindItem(images[position])
    }
}

class ImagesPeopleViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    private val imgPoster = view.find<ImageView>(R.id.imgPoster)

    fun bindItem(imagesPeople: ImagesPeople) {
        Glide.with(itemView)
            .load("https://image.tmdb.org/t/p/w500" + imagesPeople.file_path)
            .into(imgPoster)

        itemView.tvTitle.invisible()

        Log.i("Data", "file path : " + imagesPeople.file_path)

//        itemView.setOnClickListener {
//            val id = similarMovie.id

//            itemView.context.startActivity<DetailActivity>("id" to id)
//        }

    }
}