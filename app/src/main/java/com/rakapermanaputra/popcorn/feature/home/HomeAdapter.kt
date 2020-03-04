package com.rakapermanaputra.popcorn.feature.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.model.Movies

class HomeAdapter(private val data: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_HEADER = 0
        private const val ITEM_POSTER = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is String -> ITEM_HEADER
            is Movies -> ITEM_POSTER
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_HEADER -> HomeHeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false))
            ITEM_POSTER -> HomeItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_poster, parent, false))
            else -> throw throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_HEADER -> {
                val headerHolder = holder as HomeHeaderViewHolder
                headerHolder.bindItem(data[position] as String)
            }
            ITEM_POSTER -> {
                val itemHolder = holder as HomeItemHolder
                itemHolder.bindItem(data[position] as Movies)
            }
            else -> throw  IllegalArgumentException("Undefined view type")
        }

    }

}