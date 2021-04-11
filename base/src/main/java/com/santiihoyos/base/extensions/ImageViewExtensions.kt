package com.santiihoyos.base.extensions

import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.santiihoyos.base.BuildConfig
import com.squareup.picasso.Picasso

/**
 * Flag to know if picasso singleton has been created
 */
private var picassoAlreadyCreated = false

/**
 * load image from Url using Image library
 *
 * @param imageUrl url of image to load
 * @param placeHolder Place holder used in loading and error
 * @param height - required resized height
 * @param width - require resized width
 */
fun ImageView.loadFromUrl(
    imageUrl: String,
    @DrawableRes placeHolder: Int? = null,
    height: Int? = null,
    width: Int? = null
) {

    lateinit var picassoInstance: Picasso
    if (!BuildConfig.DEBUG || picassoAlreadyCreated) {

        picassoInstance = Picasso.get()
    } else if (!picassoAlreadyCreated) {

        picassoInstance = Picasso.Builder(this.context).listener { _, _, ex ->
            Log.e("PICASSO-ERROR!", "trying load: $imageUrl\n" + ex.stackTraceToString())
        }.build()
        picassoAlreadyCreated = true
        Picasso.setSingletonInstance(picassoInstance)
    }

    var requestCreator = picassoInstance.load(imageUrl)
    if (placeHolder != null) {

        requestCreator = requestCreator.placeholder(placeHolder)
    }

    requestCreator = requestCreator.fit().centerInside()

    if (height != null && width != null) {

        requestCreator = requestCreator.resize(height, width)
    }

    requestCreator.into(this)
}
