package com.rakapermanaputra.popcorn.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.model.Review
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(private val context: Context, private val review: List<Review>)
    : androidx.recyclerview.widget.RecyclerView.Adapter<ReviewViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(LayoutInflater.from(context).inflate(R.layout.item_review, parent, false))
    }

    override fun getItemCount(): Int = review.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bindItem(review[position])
    }

}

class ReviewViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    fun bindItem(review: Review) {
        itemView.tvNameReviewer.text = review.author
        itemView.tvReview.text = review.content
    }
}