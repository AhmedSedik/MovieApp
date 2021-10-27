package com.example.myapplication.ui.moviedetails

import com.example.myapplication.util.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs
import com.example.myapplication.data.model.domain.MovieDetailsDomain
import com.example.myapplication.data.model.entity.MovieDetails
import com.example.myapplication.databinding.FragmentMovieDetailsBinding
import com.example.myapplication.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    private var movieId: Long = 0

    private var movieDetails: MovieDetailsDomain? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            movieId = args.movieId
            viewModel = movieDetailsViewModel
            lifecycleOwner = viewLifecycleOwner


        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //movieDetailsViewModel.setIdMovie(movieId)



        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner){
            if (it != null) {
                when (it) {
                    is Resources.Success-> {
                        movieDetails = it.data
                        Timber.d("${it.data}")
                    }
                    is Resources.Loading -> {
                        binding.networkState.progressBar.visible()
                    }
                    is Resources.Error -> {
                        binding.networkState.errorMsg.visible()
                        binding.networkState.errorMsg.text = "Network error!"
                    }
                }
            }
        }


    }




}