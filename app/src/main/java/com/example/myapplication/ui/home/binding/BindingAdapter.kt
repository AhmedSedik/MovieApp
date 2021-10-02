package com.example.myapplication.ui.home.binding

import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.GoToMovie
import com.example.myapplication.data.InfiniteContentScrollListener
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.ui.home.adapter.MovieListAdapter
import com.example.myapplication.util.Constants
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */

/*
@BindingAdapter("bind_movie_list")
suspend fun RecyclerView.bindMovieList(
    items: PagingData<MovieDomain>?
) {
    if (items == null) return
    if (this.adapter == null) {
        this.adapter =
            MovieListAdapter()
    }
    (this.adapter as MovieListAdapter).submitData(items)
}

*/


@BindingAdapter("bind_poster_path")
fun bindPosterImageWithPicasso(imgView: ImageView,path: String?) {
    if (path.isNullOrBlank()) {
        imgView.setImageResource(R.drawable.ic_baseline_image_24)
    }
    Picasso.get().load(Constants.getPosterUrl(path)).fit()
        .transform(RoundedCornersTransformation(4,1))
        .error(R.drawable.ic_baseline_image_24).into(imgView)
}

/*@BindingAdapter("bind_progress_visibility")
fun View.bindProgressVisibility(items: List<Any>?) {
    if (items == null) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}*/

