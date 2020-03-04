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
import com.rakapermanaputra.popcorn.model.Cast
import com.rakapermanaputra.popcorn.model.Movies
import com.rakapermanaputra.popcorn.model.People
import kotlinx.android.synthetic.main.item_people.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class CasterAdapter(private val context: Context, private val caster: List<Cast>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<CasterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasterViewHolder {
        return CasterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_people, parent, false))
    }

    override fun getItemCount(): Int = caster.size

    override fun onBindViewHolder(holder: CasterViewHolder, position: Int) {
        holder.bindItem(caster[position])
    }
}

class CasterViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    private val imgPorfile = view.find<ImageView>(R.id.imgProfilePath)

    fun bindItem(cast: Cast) {
        Glide.with(itemView)
            .load("http://image.tmdb.org/t/p/w185" + cast.profile_path)
            .into(imgPorfile)

        itemView.tvName.text = cast.name
        itemView.tvCharacter.text = "as " + cast.character

        itemView.setOnClickListener {
            itemView.context.startActivity<DetailPeopleActivity>("id" to cast.id)
        }

    }
}