package com.abdelmageed.flickersimages.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.abdelmageed.flickersimages.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun Context.showToast(message: String, length: Int? = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length!!).show()
}

fun ImageView.applyImage(imageUrl: String) {
    Glide.with(this.context).load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .error(R.mipmap.ic_launcher).into(this)
}