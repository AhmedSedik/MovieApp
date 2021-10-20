package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.EventObserver
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.MovieClickListener
import com.example.myapplication.ui.home.adapter.HomeAdapter
import com.example.myapplication.util.MovieListType
import com.example.myapplication.util.Resources
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment
    : Fragment(), MovieClickListener {
    private lateinit var binding: FragmentHomeBinding
    @Inject
    lateinit var movieListType: MovieListType
    private val homeViewModel: HomeViewModel by viewModels()

    private val homeAdapter = HomeAdapter(this)

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
            lifecycleOwner = viewLifecycleOwner

        }
        binding.popularRecyclerView.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        homeViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            movies.let {
                homeAdapter.submitList(it.data)
                binding.apply {
                    popularProgressBar.isVisible =
                        it is Resources.Loading && it.data.isNullOrEmpty()
                }
            }

        })

        homeViewModel.viewAllEvent.observe(viewLifecycleOwner, EventObserver { type ->
            navigateToViewAll(type)
        })

        movieListType = MovieListType.POPULAR

        setHasOptionsMenu(true)
        return binding.root

    }

    private fun navigateToViewAll(type: MovieListType) {
        val navigateAction = HomeFragmentDirections.actionHomeFragmentToShowAllFragment(type)
        findNavController().navigate(navigateAction)
    }

    private fun navigateDetail(id: Long) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment2(id)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClick(movieData: MovieDomain) {
        navigateDetail(movieData.id)
    }


}