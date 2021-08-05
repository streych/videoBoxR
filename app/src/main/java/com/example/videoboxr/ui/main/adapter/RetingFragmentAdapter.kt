package com.example.videoboxr.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.videoboxr.R
import com.example.videoboxr.databinding.FragmentRetingRecyclerItemBinding
import com.example.videoboxr.model.data.Movie
import com.example.videoboxr.ui.main.bottommenu.favorite.reting.RetingFragment
import com.google.android.material.snackbar.Snackbar

class RetingFragmentAdapter :
    RecyclerView.Adapter<RetingFragmentAdapter.RetingViewModel>() {
    private var movieData : List<Movie> = listOf()
    private var onItemViewClickListener: RetingFragment.OnItemViewClickListener? = null

    fun setOnItemViewClickListener(onItemViewClickListener: RetingFragment.OnItemViewClickListener){
        this.onItemViewClickListener = onItemViewClickListener
    }

    fun removeOnItemViewClickListener(){
        onItemViewClickListener = null
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setMovie(data: List<Movie>){
        movieData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetingViewModel {
        val binding = FragmentRetingRecyclerItemBinding.
        inflate(LayoutInflater.from(parent.context), parent, false)
        return RetingViewModel(binding)
    }

    override fun onBindViewHolder(holder: RetingViewModel, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    inner class RetingViewModel (val binding: FragmentRetingRecyclerItemBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            //binding.retingFragmentRecyclerItemView.text = movie.title
            //binding.itemImageR.setImageIcon(R.drawable.ic_favorite)
            binding.itemImageR.setImageResource(R.drawable.image)
            binding.itemTitleR.text = movie.title
            binding.itemDataR.text = movie.dataCreate
            binding.itemRetingR.text = movie.rating.toString()
            binding.root.setOnClickListener {
                itemView.findViewById<TextView>(R.id.item_titleR).text = movie.title
                onItemViewClickListener?.onItemViewClick(movie)
                Snackbar.make(binding.root, "My Text", Snackbar.LENGTH_LONG).show()
            }
        }
    }

}