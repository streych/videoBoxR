package com.example.videoboxr.ui.main.bottommenu.favorite.reting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.videoboxr.R
import com.example.videoboxr.databinding.RetingFragmentBinding
import com.example.videoboxr.model.AppState
import com.example.videoboxr.ui.main.adapter.RetingFragmentAdapter
import com.example.videoboxr.ui.main.detailfragment.DetailFragment
import com.example.videoboxr.ui.main.hide
import com.example.videoboxr.ui.main.show
import com.example.videoboxr.ui.main.showSnakeBar

class RetingFragment : Fragment() {

    private var _binding: RetingFragmentBinding? = null

    private val binding
        get() = _binding!!
    private val adapter = RetingFragmentAdapter()

    companion object {
        fun newInstance() = RetingFragment()
    }

    private val viewModel: RetingViewModel by lazy {
        ViewModelProvider(this).get(RetingViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RetingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnItemViewClickListener { movie ->
            activity?.supportFragmentManager?.apply {
                beginTransaction().replace(
                    R.id.container,
                    DetailFragment.newInstance(Bundle().apply {
                        putParcelable(DetailFragment.BUNDLE_EXTRA, movie)
                    })
                )
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }

        binding.retingFragmentRecycleView.adapter = adapter
        viewModel.getData().observe(viewLifecycleOwner, Observer { a -> renderData(a) })
    }

    private fun renderData(data: AppState) = with(binding.loadingLayout) {
        when (data) {
            is AppState.Success -> {
                hide()
                adapter.setMovie(data.movieData)
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