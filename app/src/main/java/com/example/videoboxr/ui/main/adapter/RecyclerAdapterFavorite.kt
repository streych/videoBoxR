package com.example.videoboxr.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.videoboxr.R
import com.example.videoboxr.model.data.Movie

class RecyclerAdapterFavorite :
    RecyclerView.Adapter<RecyclerAdapterFavorite.ViewHolder>() {
    private var movieData: List<Movie> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setMovie(data: List<Movie>){
        movieData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterFavorite.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout_favorite, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterFavorite.ViewHolder, position: Int) {
        with(holder) {
            with(movieData[position]) {
                itemTitle.text = title
                itemData.text = dataCreate
                itemReting.text = rating.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemData: TextView
        var itemReting: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_imageF)
            itemTitle = itemView.findViewById(R.id.item_titleF)
            itemData = itemView.findViewById(R.id.item_dataF)
            itemReting = itemView.findViewById(R.id.item_retingF)

            itemView.setOnClickListener {
                val position: Int = adapterPosition
            }
        }

    }
}