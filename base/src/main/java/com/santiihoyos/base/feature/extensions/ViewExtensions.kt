@file:Suppress("UsePropertyAccessSyntax")

package com.santiihoyos.base.feature.extensions

import android.view.View

/**
 * Sets View visibility = View.VISIBLE
 */
fun View?.visible() = this?.setVisibility(View.VISIBLE)

/**
 * Sets View visibility = View.INVISIBLE
 */
fun View?.invisible() = this?.setVisibility(View.INVISIBLE)

/**
 * Sets View visibility = View.GONE
 */
fun View?.gone() = this?.setVisibility(View.GONE)
