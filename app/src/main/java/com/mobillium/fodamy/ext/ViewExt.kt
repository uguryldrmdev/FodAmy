package com.mobillium.fodamy.ext

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.visible(isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean){
    isEnabled = enabled
}