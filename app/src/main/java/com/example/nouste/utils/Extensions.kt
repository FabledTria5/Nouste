package com.example.nouste.utils

import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.AnimationUtils
import co.revely.gradient.RevelyGradient
import com.example.nouste.R
import com.example.nouste.enums.Gradients
import com.makeramen.roundedimageview.RoundedImageView
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

fun <T> List<T>.getSubList(endIndex: Int): List<T> {
    return if (this.count() > endIndex) {
        this.subList(0, endIndex)
    } else {
        this
    }
}

fun Drawable?.convertToByteArray(): ByteArray? {
    if (this == null) return null
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

fun RoundedImageView.setImageGradient(gradient: Gradients) {
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

fun View.setGradient(gradient: Gradients) {
    RevelyGradient
        .linear()
        .colors(
            intArrayOf(
                Color.parseColor(gradient.startColor),
                Color.parseColor(gradient.endColor)
            )
        )
        .angle(45f)
        .onBackgroundOf(this)
}

fun View.rotate(startDegree: Float) {
    val anim = ObjectAnimator
        .ofFloat(this, "rotation", startDegree, startDegree + 180)
    anim.duration = 500
    anim.start()
}

fun View.setZoom(isZoom: Boolean) {
    val animationResource = if (isZoom) R.anim.zoom_in else R.anim.zoom_out
    val animation = AnimationUtils.loadAnimation(this.context, animationResource)
    this.startAnimation(animation)
}

fun Int.toDp(density: Float) = (this * density).roundToInt()

fun ByteArray.toDrawable(): Bitmap = BitmapFactory.decodeByteArray(this, 0, this.size)

