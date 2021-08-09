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
import com.example.videoboxr.R
import com.example.videoboxr.databinding.FavoriteFragmentBinding
import com.example.videoboxr.model.AppState
import com.example.videoboxr.ui.main.adapter.RecyclerAdapterFavorite
import com.example.videoboxr.ui.main.hide
import com.example.videoboxr.ui.main.show
import com.example.videoboxr.ui.main.showSnakeBar

class FavoriteFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapterViewHolderFavorite: RecyclerView.Adapter<RecyclerAdapterFavorite.ViewHolder>? =
        null
    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!
    private var recyclerAdapterFavorite = RecyclerAdapterFavorite()

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private val favoriteViewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this).get(FavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layoutManager = LinearLayoutManager(context)
        adapterViewHolderFavorite = RecyclerAdapterFavorite()

        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel.getData().observe(viewLifecycleOwner, Observer { a -> renderData(a) })
    }

    private fun renderData(data: AppState) =
        with(binding.loadingLayout) {
            when (data) {
                is AppState.Success -> {
                    hide()
                    recyclerAdapterFavorite.setMovie(data.movieData)
                }
                is AppState.Loading -> {
                    show()
                }
                is AppState.Error -> {
                    hide()
                    showSnakeBar(
                        getString(R.string.error_info),
                        getString(R.string.reload)
                    ) {}
                }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}