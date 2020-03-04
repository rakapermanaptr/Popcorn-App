package com.rakapermanaputra.popcorn.feature.home

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.rakapermanaputra.popcorn.R

class HomeHeaderViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    private val itemHeader = itemView.findViewById(R.id.tv_header) as TextView

    fun bindItem(text: String) {
        itemHeader.text = text
    }
}