package com.example.nouste.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import co.revely.gradient.RevelyGradient
import com.makeramen.roundedimageview.RoundedImageView
import java.io.ByteArrayOutputStream

fun <T> List<T>.getSubList(endIndex: Int): List<T> {
    return if (this.count() > endIndex) {
        this.subList(0, endIndex)
    } else {
        this
    }
}

fun Drawable.convertToByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    (this as BitmapDrawable).bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    return stream.toByteArray()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun RoundedImageView.setGradient(gradient: Gradients) {
    RevelyGradient
        .linear()
        .colors(
            intArrayOf(
                Color.parseColor(gradient.startColor),
                Color.parseColor(gradient.endColor)
            )
        )
        .angle(45f)
        .on(this)
}