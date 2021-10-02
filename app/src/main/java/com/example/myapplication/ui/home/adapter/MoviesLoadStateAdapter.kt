package com.example.myapplication.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemLoadStateBinding

class MoviesLoadStateAdapter(
    private val retryListener: () -> Unit
) : LoadStateAdapter<MoviesLoadStateAdapter.LoadStateItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateItemViewHolder {
        return LoadStateItemViewHolder(parent, retryListener = retryListener)
    }

    override fun onBindViewHolder(holder: LoadStateItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoadStateItemViewHolder(
        parent: ViewGroup,
        retryListener: () -> Unit
    ) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_load_state, parent, false)) {

        private val binding = ItemLoadStateBinding.bind(itemView)

        init {
            binding.retryButton.setOnClickListener {
                retryListener.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
                errorMsg.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorMsg.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }
}

