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
import com.rakapermanaputra.popcorn.model.Category
import com.rakapermanaputra.popcorn.model.Movies
import kotlinx.android.synthetic.main.item_home_category.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeCategoryAdapter(private val context: Context, private val categories: List<Category>) :
    RecyclerView.Adapter<HomeCatogoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCatogoryViewHolder {
        return HomeCatogoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_category, parent, false))
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: HomeCatogoryViewHolder, position: Int) {
        holder.bindItem(categories[position])
    }
}

class HomeCatogoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(category: Category) {
        itemView.tvCategory.text = category.genres

        itemView.setOnClickListener {
            itemView.context.toast(category.genres)
        }
    }
}