package com.example.myapplication.ui.home.binding


import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.myapplication.R
import com.example.myapplication.util.Constants
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */

/*
@BindingAdapter("bind_movie_list", "bind_load_more", "bind_view_model")
fun RecyclerView.bindMovieList(
    items: List<MovieDto>?,
    loadMoreContent: () -> Unit,
    goTo: GoToMovie
) {
    if (items == null) return
    if (this.adapter == null) {
        this.adapter =
            HomeAdapter(goTo, InfiniteContentScrollListener(this) {
                loadMoreContent() })
    }
    (this.adapter as HomeAdapter).submitList(items)
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

@BindingAdapter("bind_progress_visibility")
fun View.bindProgressVisibility(items: List<Any>?) {
    if (items == null) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

