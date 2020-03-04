package com.rakapermanaputra.popcorn.feature.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.rakapermanaputra.popcorn.R

class HomeHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val itemHeader = itemView.findViewById(R.id.tv_header) as TextView

    fun bindItem(text: String) {
        itemHeader.text = text
    }
}