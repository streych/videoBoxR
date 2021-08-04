package com.example.videoboxr.ui.main.bottommenu.favorite.reting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.videoboxr.databinding.RetingFragmentBinding

class RetingFragment : Fragment() {

    private var _binding: RetingFragmentBinding? = null

    private  val binding
    get() = _binding!!

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
        viewModel = ViewModelProvider(this).get(RetingViewModel::class.java)
    }

}