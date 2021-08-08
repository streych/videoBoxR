package com.example.videoboxr.ui.main.bottommenu.favorite.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.videoboxr.R
import com.example.videoboxr.databinding.MainFragmentBinding
import com.example.videoboxr.model.AppState
import com.example.videoboxr.model.data.Movie
import com.example.videoboxr.ui.main.adapter.RecyclerAdapterMain
import com.example.videoboxr.ui.main.detailfragment.DetailFragment
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = RecyclerAdapterMain()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter.removeOnItemViewClickListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnItemViewClickListener(object : MainFragment.OnItemViewClickListenerMain {
            override fun onItemViewClick(movie: Movie) {
                val manager = activity?.supportFragmentManager
                if (manager != null) {
                    val bundle = Bundle()
                    //заменить!!!!!!!!!!!!
                    bundle.putParcelable(DetailFragment.BUNDLE_EXTRA, movie)
                    manager.beginTransaction().replace(
                        R.id.container,
                        DetailFragment.newInstance(bundle)
                    )
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }

        })
        binding.recycleNowPlaying.adapter = adapter
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, Observer { a -> renderData(a) })
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                val movieData = data.movieData
                binding.loadingLayout.visibility = View.GONE
                adapter.setMovie(movieData)
                //populateData(movieData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(
                    binding.main,
                    "Error connect to database, please check your password",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    interface OnItemViewClickListenerMain {
        fun onItemViewClick(movie: Movie)
    }

    private fun populateData(movieData: Movie) {
        with(binding) {

        }
    }

}