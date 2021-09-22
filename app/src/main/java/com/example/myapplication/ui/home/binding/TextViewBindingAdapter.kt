package com.example.myapplication.ui.home.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * Created by Ahmad Sedeek on 9/22/2021.
 */

@BindingAdapter("bind_rating_text")
fun bindRatingTextView(txtView: TextView, rating: Double?) {
    txtView.text = rating.toString()
}