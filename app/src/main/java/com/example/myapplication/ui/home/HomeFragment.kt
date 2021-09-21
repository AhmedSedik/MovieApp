package com.example.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.model.EventObserver
import com.example.myapplication.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            viewModel = homeViewModel
            lifecycleOwner = this@HomeFragment.viewLifecycleOwner



        }

        homeViewModel.viewAllEvent.observe(viewLifecycleOwner, EventObserver {
            val navigateAction = HomeFragmentDirections.actionHomeFragmentToShowAllFragment(it)
            findNavController().navigate(navigateAction)

        })

        return binding.root
    }

}