package com.example.myapplication.ui.view_all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentViewallBinding
import com.example.myapplication.ui.home.adapter.MovieListAdapter
import com.example.myapplication.ui.home.adapter.MoviesLoadStateAdapter
import com.example.myapplication.util.MovieListType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * This @MovieFragment will hold all movies list ( popular, InTheater, Upcoming etc,..)
 *
 */
@AndroidEntryPoint
class ViewAllFragment : Fragment() {

    private lateinit var binding: FragmentViewallBinding
    private val args: ViewAllFragmentArgs by navArgs()

    private val pagingAdapter = MovieListAdapter()

    private val viewAllViewModel: ViewAllViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewallBinding.inflate(
            inflater,
            container, false)


        binding.viewModel = viewAllViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val current = args.movieListTypeArgs
        setupView()
        observeViewModel(current)
    }

    private fun observeViewModel(movieListType: MovieListType) {
        lifecycleScope.launch {
            viewAllViewModel.getMoviesFlow(movieListType).distinctUntilChanged() .collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }


    private fun setupView() {
        binding.showAllRecyclerview.apply {
            val gridLayoutManager = GridLayoutManager(requireContext(),3)
            layoutManager = gridLayoutManager
            adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                header = MoviesLoadStateAdapter(retryListener = { pagingAdapter.retry() }),
                footer = MoviesLoadStateAdapter(retryListener = { pagingAdapter.retry() })
            )
        }
    }
}