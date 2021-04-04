package com.santiihoyos.base.feature.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadFromUrl(imageUrl: String) {

    Picasso.get().load(imageUrl).into(this)
}
