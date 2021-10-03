package com.example.myapplication.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.myapplication.data.GoToMovie
import com.example.myapplication.data.InfiniteContentScrollListener
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.remote.MovieDto
import com.example.myapplication.databinding.ListItemMovieBinding
import com.example.myapplication.databinding.ListItemMovieGridBinding

class HomeAdapter internal constructor(
    private val goToMovie: GoToMovie,
    private val infiniteContentScrollListener: InfiniteContentScrollListener
) : ListAdapter<(MovieDto), HomeAdapter.ViewHolder>(MovieDiffCallback()) {

    private var isMovieItemGrid: Boolean = false

    class ViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(GoToMovie: GoToMovie, item: MovieDto) {
            when (binding) {
                is ListItemMovieBinding -> {
                    binding.goToInterface = GoToMovie
                    binding.movie = item
                    binding.executePendingBindings()
                }
               /* is ListItemMovieGridBinding -> {
                    binding.goToInterface = GoToMovie
                    binding.movie = item
                    binding.executePendingBindings()
                }*/
                else -> throw Exception("Invalid list binding")
            }
        }
    }

    override fun submitList(list: List<MovieDto>?) {
        val newList: MutableList<MovieDto> = arrayListOf()
        if (list != null) newList.addAll(list)
        super.submitList(newList)
        infiniteContentScrollListener.itemsLoaded()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(goToMovie, getItem(position))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (recyclerView.layoutManager is GridLayoutManager) isMovieItemGrid = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = if (isMovieItemGrid) {
            ListItemMovieGridBinding.inflate(layoutInflater, parent, false)
        } else {
            ListItemMovieBinding.inflate(layoutInflater, parent, false)
        }
        return ViewHolder(binding)
    }

    private class MovieDiffCallback : DiffUtil.ItemCallback<MovieDto>() {
        override fun areItemsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
