package com.example.videoboxr.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.videoboxr.R
import com.example.videoboxr.databinding.FragmentFavoriteRecyclerItemBinding
import com.example.videoboxr.model.data.Movie
import com.example.videoboxr.ui.main.bottommenu.favorite.FavoriteViewModel

class RecyclerAdapterFavorite :
    RecyclerView.Adapter<RecyclerAdapterFavorite.FavoriteViewModel>() {
    private var movieData: List<Movie> = listOf()
    private var onItemViewClickListener: (Movie) -> Unit = {}

    fun setOnItemViewClickListener(onItemViewClickListener: (Movie) -> Unit = {}){
        this.onItemViewClickListener = onItemViewClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovie(data: List<Movie>){
        movieData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterFavorite.FavoriteViewModel {
        val binding = FragmentFavoriteRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false)
        return FavoriteViewModel(binding)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterFavorite.FavoriteViewModel, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    inner class FavoriteViewModel(val binding: FragmentFavoriteRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                with(movie) {
                    itemImageF.setImageResource(R.drawable.image)
                    itemTitleF.text = title
                    itemDataF.text = dataCreate
                    itemRetingF.text = rating.toString()
                    root.setOnClickListener {
                        //itemView.findViewById<TextView>(R.id.item_titleF).text = title
                        onItemViewClickListener(this)
                    }
                }
            }
        }
    }
}