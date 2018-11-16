package com.rafakob.android.kotlinextensions

import android.graphics.PorterDuff
import androidx.annotation.ColorInt
import android.widget.ImageView

fun ImageView.tint(@ColorInt color: Int, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN) {
    setColorFilter(color, mode)
}
