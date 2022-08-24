package com.robsonribeiroft.chuckjokes.core

import android.view.View

fun View.gone() { visibility = View.GONE }
fun View.visible() { visibility = View.VISIBLE }

fun View.goneWithFadeOutAnimation() {
    animate()
        .alpha(0.0f)
        .duration = 300L
    gone()
}


fun View.visibleWithFadeInAnimation() {
    alpha = 0.0f
    visible()
    animate()
        .alpha(1.0f)
        .duration = 300L
}