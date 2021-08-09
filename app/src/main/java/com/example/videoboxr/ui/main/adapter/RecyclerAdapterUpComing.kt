package com.example.videoboxr.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoboxr.R
import com.example.videoboxr.databinding.FragmentNowPlaingItemBinding
import com.example.videoboxr.databinding.FragmentUpComingItemBinding
import com.example.videoboxr.model.data.Movie

class RecyclerAdapterUpComing : RecyclerView.Adapter<RecyclerAdapterUpComing.UpComingViewHolder>() {

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
    ): RecyclerAdapterUpComing.UpComingViewHolder {
        val binding =
            FragmentUpComingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpComingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerAdapterUpComing.UpComingViewHolder,
        position: Int
    ) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    inner class UpComingViewHolder(val binding: FragmentUpComingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                with(movie) {
                    imageUpComing.setImageResource(R.drawable.image)
                    titleUpComing.text = title
                    dataUpComing.text = dataCreate
                    retingUpComing.text = rating.toString()
                    root.setOnClickListener {
                        onItemViewClickListener(this)
                    }
                }
            }
        }

    }
}