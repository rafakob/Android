package com.rafakob.android.kotlinextensions

import android.graphics.PorterDuff
import android.support.annotation.ColorInt
import android.view.Menu

fun Menu.tintIcons(@ColorInt color: Int) {
    for (i in 0 until size()) {
        if (getItem(i).hasSubMenu()) {
            getItem(i).subMenu.tintIcons(color)
        } else {
            getItem(i).icon.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }
}