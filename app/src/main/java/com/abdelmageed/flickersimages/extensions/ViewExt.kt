package com.abdelmageed.flickersimages.extensions

import android.view.View

fun View.viewIsVisible() {
    this.visibility = View.VISIBLE
}

fun View.viewIsGone() {
    this.visibility = View.GONE
}