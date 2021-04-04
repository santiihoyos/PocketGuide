package com.santiihoyos.base.feature.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso

fun ImageView.loadFromUrl(imageUrl: String, @DrawableRes placeHolder: Int? = null) {

    if (placeHolder == null ) {

        Picasso.get().load(imageUrl).fit().centerInside().into(this)
    } else {

        Picasso.get().load(imageUrl).placeholder(placeHolder).fit().centerInside().into(this)
    }

}
