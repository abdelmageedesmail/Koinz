package com.abdelmageed.flickersimages.extensions

fun String.createImageUrl(frame: String, server: String, id: String, secret: String): String {
    return "$this$frame.static.flickr.com/$server/${id}_$secret.jpg"
}