package com.example.videoboxr.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.videoboxr.R
import com.example.videoboxr.databinding.FragmentRetingRecyclerItemBinding
import com.example.videoboxr.model.data.Movie
import com.example.videoboxr.ui.main.bottommenu.favorite.reting.RetingFragment
//ока не трогал скоро переделаю
class RetingFragmentAdapter :
    RecyclerView.Adapter<RetingFragmentAdapter.RetingViewModel>() {
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
        fun bind(movie: Movie) {
            with(binding) {
                with(movie) {
                    itemImageR.setImageResource(R.drawable.image)
                    itemTitleR.text = title
                    itemDataR.text = dataCreate
                    itemRetingR.text = rating.toString()
                    root.setOnClickListener {
                        itemView.findViewById<TextView>(R.id.item_titleR).text = title
                        onItemViewClickListener(this)
                    }
                }
            }
        }
    }

}