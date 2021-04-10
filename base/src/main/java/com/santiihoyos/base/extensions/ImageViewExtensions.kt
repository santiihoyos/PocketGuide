package com.santiihoyos.base.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso

fun ImageView.loadFromUrl(
    imageUrl: String,
    @DrawableRes placeHolder: Int? = null,
    height: Int? = null,
    width: Int? = null
) {

    var picasso = Picasso.get().load(imageUrl)

    if (placeHolder != null ) {

        picasso = picasso.placeholder(placeHolder)
    }

    picasso = picasso.fit().centerInside()

    if (height != null && width != null) {

        picasso = picasso.resize(height, width)
    }

    picasso.into(this)
}
