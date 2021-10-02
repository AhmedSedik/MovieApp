package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.EventObserver
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.home.adapter.MovieListAdapter
import com.example.myapplication.ui.home.adapter.MoviesLoadStateAdapter
import com.example.myapplication.util.MovieListType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    @Inject lateinit var movieListType: MovieListType
    private val homeViewModel: HomeViewModel by viewModels()

    private val pagingAdapter = MovieListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        binding.lifecycleOwner = this@HomeFragment.viewLifecycleOwner
        binding.viewModel = homeViewModel


        movieListType = MovieListType.POPULAR

        setHasOptionsMenu(true)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeViewModel()



        homeViewModel.viewAllEvent.observe(viewLifecycleOwner, EventObserver { type ->
            navigateToViewAll(type)
        })
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            homeViewModel.getMovies(movieListType).distinctUntilChanged().collectLatest {
                pagingAdapter.submitData(it)
            }
        }

    }

    private fun navigateToViewAll(type: MovieListType) {
        val navigateAction = HomeFragmentDirections.actionHomeFragmentToShowAllFragment(type)
        findNavController().navigate(navigateAction)
    }


    private fun setupView() {
        binding.popularRecyclerView.apply {
            val horizontalLayoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)


            layoutManager = horizontalLayoutManager
            adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                header = MoviesLoadStateAdapter(retryListener = { pagingAdapter.retry() }),
                footer = MoviesLoadStateAdapter(retryListener = { pagingAdapter.retry() })
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}