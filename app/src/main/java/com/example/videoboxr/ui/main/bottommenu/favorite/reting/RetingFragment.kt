package com.example.videoboxr.ui.main.bottommenu.favorite.reting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.videoboxr.R
import com.example.videoboxr.databinding.RetingFragmentBinding
import com.example.videoboxr.model.AppState
import com.example.videoboxr.model.data.Movie
import com.example.videoboxr.ui.main.adapter.RetingFragmentAdapter
import com.example.videoboxr.ui.main.detailfragment.DetailFragment
import com.example.videoboxr.ui.main.upmenu.settings.SettingsFragment
import com.google.android.material.snackbar.Snackbar

class RetingFragment : Fragment() {

    private var _binding: RetingFragmentBinding? = null

    private  val binding
    get() = _binding!!
    private val adapter = RetingFragmentAdapter()

    companion object {
        fun newInstance() = RetingFragment()
    }

    private lateinit var viewModel: RetingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RetingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnItemViewClickListener(object : OnItemViewClickListener{
            override fun onItemViewClick(movie: Movie) {
                val manager = activity?.supportFragmentManager
                if (manager != null){
                    val bundle = Bundle()
                    bundle.putParcelable(DetailFragment.BUNDLE_EXTRA, movie)
                    manager.beginTransaction().replace(R.id.container,
                        DetailFragment.newInstance(bundle))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }

        })
        binding.retingFragmentRecycleView.adapter = adapter

        viewModel = ViewModelProvider(this).get(RetingViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, Observer { a -> renderData(a) })
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                val movieData = data.movieData
                binding.loadingLayout.visibility = View.GONE
                adapter.setMovie(movieData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.retingFragment, "Error connect to database, please check your password", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    interface OnItemViewClickListener{
        fun onItemViewClick(movie: Movie)
    }

    private fun populateData(movieData: Movie){
        with(binding){

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter.removeOnItemViewClickListener()
    }

}