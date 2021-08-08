package com.example.videoboxr.ui.main.detailfragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.videoboxr.R
import com.example.videoboxr.databinding.DetailFragmentBinding
import com.example.videoboxr.model.data.Movie
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(bundle: Bundle) : DetailFragment{
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
        const val BUNDLE_EXTRA = "movie"
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val movie = arguments?.getParcelable<Movie>(BUNDLE_EXTRA)
        if (movie != null){
            populateData(movie)
        }
    }

    private fun populateData(movieData: Movie){
        with(binding){
            itemTitle.text = movieData.title
            itemData.text = movieData.dataCreate
            itemDescription.text = movieData.description
            itemRetingR.text = movieData.rating.toString()
            item_image.setImageResource(R.drawable.image)
        }
    }

}