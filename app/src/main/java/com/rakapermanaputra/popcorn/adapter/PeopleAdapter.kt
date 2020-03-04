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
import com.rakapermanaputra.popcorn.feature.detail.detail_people.DetailPeopleActivity
import com.rakapermanaputra.popcorn.model.People
import kotlinx.android.synthetic.main.item_people.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class PeopleAdapter(private val context: Context, private val peoples: List<People>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_people, parent, false))
    }

    override fun getItemCount(): Int = peoples.size

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bindItem(peoples[position])
    }
}

class PeopleViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    private val imgPorfile = view.find<ImageView>(R.id.imgProfilePath)

    fun bindItem(people: People) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w185" + people.profilePath)
            .into(imgPorfile)

        itemView.tvName.text = people.name

        itemView.setOnClickListener {
            itemView.context.startActivity<DetailPeopleActivity>("id" to people.id)
        }

    }
}