package com.example.myapplication.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.myapplication.data.GoToMovie
import com.example.myapplication.data.InfiniteContentScrollListener
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.databinding.ItemSliderMovieBinding
import com.example.myapplication.databinding.ListItemMovieBinding
import com.example.myapplication.databinding.ListItemMovieGridBinding
import java.lang.Exception

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */

class MovieListAdapter(
    private val goToMovie: GoToMovie,
    private val infiniteContentScrollListener: InfiniteContentScrollListener
) : ListAdapter<(Movie), MovieListAdapter.MovieViewHolder>(MovieDiffCallback) {

    private var isGrid: Boolean = false

    class MovieViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(goToMovie: GoToMovie,movie: Movie) {

            when (binding) {
                is ListItemMovieBinding -> {
                    binding.gotoInterface = goToMovie
                    binding.movie = movie
                    binding.executePendingBindings()
                }
                is ListItemMovieGridBinding -> {
                    binding.gotoInterface = goToMovie
                    binding.movie = movie
                    binding.executePendingBindings()
                }
                else -> throw Exception("Invalid Binding")
            }

        }


    }

    override fun submitList(list: List<Movie>?) {
        val newList: MutableList<Movie> = arrayListOf()
        if (list != null) newList.addAll(list)
        super.submitList(newList)
         infiniteContentScrollListener.itemsLoaded()
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Movie]
     * has been updated.
     */
    companion object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewBinding = if (isGrid) {
            ListItemMovieGridBinding.inflate(layoutInflater, parent, false)

        } else {
            ListItemMovieBinding.inflate(layoutInflater, parent, false)
        }
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position).let { movie->
            holder.bindView(goToMovie,movie)
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (recyclerView.layoutManager is GridLayoutManager) isGrid = true
    }

}
