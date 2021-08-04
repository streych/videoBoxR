package com.example.videoboxr.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoboxr.databinding.FragmentRetingRecyclerItemBinding
import com.example.videoboxr.model.data.Movie
import com.google.android.material.snackbar.Snackbar

class RetingFragmentAdapter :
    RecyclerView.Adapter<RetingFragmentAdapter.RetingViewModel>() {
    private var movieData : List<Movie> = listOf()

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

    class RetingViewModel (val binding: FragmentRetingRecyclerItemBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            binding.retingFragmentRecyclerItemView.text = movie.title
            binding.root.setOnClickListener {
                Snackbar.make(binding.root, "My Text", Snackbar.LENGTH_LONG).show()
            }
        }
    }

}