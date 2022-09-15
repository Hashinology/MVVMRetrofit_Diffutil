package com.hashinology.mvvmretrofit_diffutil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hashinology.mvvmretrofit_diffutil.R
import com.hashinology.mvvmretrofit_diffutil.models.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewsHolder>() {
    private val differCallBack = object: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return  oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewsHolder {
        val views = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ViewsHolder(views)
    }

    override fun onBindViewHolder(holder: ViewsHolder, position: Int) {
        val data = differ.currentList[position]
        holder.movieName.text = data.name
        Glide.with(holder.itemView.context)
            .load(data.imageUrl)
            .into(holder.movieImg)
        holder.movieDesc.text = data.desc
        holder.movieCategory.text = data.category
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewsHolder(itemviews: View): RecyclerView.ViewHolder(itemviews) {
        val movieName = itemView.findViewById<TextView>(R.id.tvMovieName)
        val movieImg = itemView.findViewById<ImageView>(R.id.ivMovie)
        val movieDesc = itemView.findViewById<TextView>(R.id.tvMovieDesc)
        val movieCategory = itemView.findViewById<TextView>(R.id.tvMovieCategory)
    }
}