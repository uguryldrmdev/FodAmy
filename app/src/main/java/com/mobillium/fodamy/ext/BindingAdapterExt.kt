package com.mobillium.fodamy.ext

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mobillium.fodamy.R

@BindingAdapter("loadcircle")
fun loadImageCircle(view: ImageView, url: String?){
    Glide.with(view)
        .load(url)
        .placeholder(R.drawable.default_user)
        .error(R.drawable.default_user)
        .fallback(R.drawable.default_user)
        .circleCrop()
        .into(view)
}
@BindingAdapter("load")
fun loadImage(view: ImageView, url: String?){
    Glide.with(view)
        .load(url)
        .into(view)
}
@BindingAdapter("visibility")
fun setVisibility(view: View, value: Boolean) {
    view.setVisibility(if (value) View.VISIBLE else View.GONE)
}