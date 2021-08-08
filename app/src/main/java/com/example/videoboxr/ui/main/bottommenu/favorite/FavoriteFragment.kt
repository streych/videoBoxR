package com.example.videoboxr.ui.main.bottommenu.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videoboxr.databinding.FavoriteFragmentBinding
import com.example.videoboxr.model.AppState
import com.example.videoboxr.ui.main.adapter.RecyclerAdapterFavorite
import com.google.android.material.snackbar.Snackbar

class FavoriteFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapterViewHolderFavorite: RecyclerView.Adapter<RecyclerAdapterFavorite.ViewHolder>? =
        null

    private var  _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!
    private var recyclerAdapterFavorite = RecyclerAdapterFavorite()
    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutManager = LinearLayoutManager(context)
        adapterViewHolderFavorite = RecyclerAdapterFavorite()

        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        favoriteViewModel.getData().observe(viewLifecycleOwner, Observer { a -> renderData(a) })
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                val movieData = data.movieData
                binding.loadingLayout.visibility = View.GONE
                recyclerAdapterFavorite.setMovie(movieData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(
                    binding.favoritesFragment,
                    "Error connect to database, please check your password",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}