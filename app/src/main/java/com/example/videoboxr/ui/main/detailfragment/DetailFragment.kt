package com.example.videoboxr.ui.main.detailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.videoboxr.R
import com.example.videoboxr.databinding.DetailFragmentBinding
import com.example.videoboxr.model.data.Movie
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(bundle: Bundle): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }

        const val BUNDLE_EXTRA = "movie"
    }

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Movie>(BUNDLE_EXTRA)?.let { movie ->
            populateData(movie)
        }
    }

    private fun populateData(movieData: Movie){
        with(binding){
            with(movieData) {
                itemTitle.text = title
                itemData.text = dataCreate
                itemDescription.text = description
                itemRetingR.text = rating.toString()
            }
            item_image.setImageResource(R.drawable.image)
        }
    }

}