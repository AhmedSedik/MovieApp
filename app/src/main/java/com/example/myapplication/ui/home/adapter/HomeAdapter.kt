package com.example.myapplication.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.databinding.ListItemMovieBinding
import com.example.myapplication.databinding.ListItemMovieGridBinding
import com.example.myapplication.ui.MovieClickListener

class HomeAdapter internal constructor(
    private val clickListener: MovieClickListener
) : ListAdapter<(MovieDomain), HomeAdapter.ViewHolder>(MovieDiffCallback()) {

    private var isMovieItemGrid: Boolean = false

    inner class ViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieDomain, clickListener: MovieClickListener) {
            when (binding) {
                is ListItemMovieBinding -> {
                    //binding.goToInterface = GoToMovie
                    binding.movie = movie
                    binding.executePendingBindings()
                    binding.clickListener = clickListener


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

    override fun submitList(list: List<MovieDomain>?) {
        val newList: MutableList<MovieDomain> = arrayListOf()
        if (list != null) newList.addAll(list)
        super.submitList(newList)
        //infiniteContentScrollListener.itemsLoaded()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
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

    private class MovieDiffCallback : DiffUtil.ItemCallback<MovieDomain>() {
        override fun areItemsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
