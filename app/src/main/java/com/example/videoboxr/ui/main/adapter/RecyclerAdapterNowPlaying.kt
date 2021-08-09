package com.example.videoboxr.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoboxr.R
import com.example.videoboxr.databinding.FragmentNowPlaingItemBinding
import com.example.videoboxr.model.data.Movie

class RecyclerAdapterNowPlaying : RecyclerView.Adapter<RecyclerAdapterNowPlaying.NowPlayingViewModel>() {

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
    ): RecyclerAdapterNowPlaying.NowPlayingViewModel {
        val binding =
            FragmentNowPlaingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NowPlayingViewModel(binding)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterNowPlaying.NowPlayingViewModel, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    inner class NowPlayingViewModel(val binding: FragmentNowPlaingItemBinding) :
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