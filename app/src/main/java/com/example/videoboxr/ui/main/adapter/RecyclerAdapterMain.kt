package com.example.videoboxr.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoboxr.R
import com.example.videoboxr.databinding.FragmentNowPlaingItemBinding
import com.example.videoboxr.model.data.Movie
import com.example.videoboxr.ui.main.bottommenu.favorite.home.MainFragment

class RecyclerAdapterMain : RecyclerView.Adapter<RecyclerAdapterMain.MainViewModel>() {

    private var movieData: List<Movie> = listOf()

    private var onItemViewClickListener: (Movie) -> Unit = {}

    fun setOnItemViewClickListener(onItemViewClickListener: (Movie) -> Unit = {}) {
        this.onItemViewClickListener = onItemViewClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovie(data: List<Movie>) {
        movieData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterMain.MainViewModel {
        val binding =
            FragmentNowPlaingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewModel(binding)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterMain.MainViewModel, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    inner class MainViewModel(val binding: FragmentNowPlaingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                with(movie) {
                    imageNowPlaying.setImageResource(R.drawable.image)
                    titleNowPlaying.text = title
                    dataNowPlaying.text = dataCreate
                    retingNowPlaying.text = rating.toString()
                    root.setOnClickListener {
                        onItemViewClickListener(this)
                    }
                }
            }
        }

    }

}