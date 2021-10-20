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

    private lateinit var movie: Resources<MovieDetailsDomain>

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
            movieId = args.id
            viewModel = movieDetailsViewModel
            lifecycleOwner = viewLifecycleOwner


        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //movieDetailsViewModel.setIdMovie(movieId)



        movieDetailsViewModel.getMovieById(movieId).observe(viewLifecycleOwner, Observer {  movies->
            movies.let {
                binding.movie = it.data
            }
        })

    }




}