package com.mrtwoza.marvel.app.listComics.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mrtwoza.marvel.R
import com.mrtwoza.marvel.app.listComics.model.Comic
import com.mrtwoza.marvel.utils.Loader

class ComicsAdapter(
    private val listener:View.OnClickListener,
    private val loader: Loader
): RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {

    private var listComics:ArrayList<Comic> = mutableListOf<Comic>() as ArrayList<Comic>

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.comic_name)
        var image: ImageView = view.findViewById(R.id.comic_image)
        var viewParent: CardView = view.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.comic_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listComics.size
    }

    fun getItem(position: Int): Comic{
        return listComics.get(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = listComics.get(position)
        holder.title.setText(comic.title)
        val url = "${comic.thumbnail.path}.${comic.thumbnail.extension}"
        Glide.with(holder.image.context).load(url).into(holder.image)

        holder.viewParent.tag = position
        holder.viewParent.setOnClickListener(listener)
        Log.i("image_comic","url $url")

        if (position == listComics.size-1){
            loader.loadData()
        }
    }

    fun updateList(list: List<Comic>){
        listComics.addAll(list)
        notifyDataSetChanged()
    }
}