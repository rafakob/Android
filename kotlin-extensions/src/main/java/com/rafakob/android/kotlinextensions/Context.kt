package com.rafakob.android.kotlinextensions

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager

@ColorInt
fun Context.getColorPrimary(): Int {
    return getColorFromAttr(R.attr.colorPrimary)
}

@ColorInt
fun Context.getColorPrimaryDark(): Int {
    return getColorFromAttr(R.attr.colorPrimaryDark)
}

@ColorInt
fun Context.getColorAccent(): Int {
    return getColorFromAttr(R.attr.colorAccent)
}

@ColorInt
fun Context.getTextColorPrimary(): Int {
    return getColorFromAttr(android.R.attr.textColorPrimary)
}

@ColorInt
fun Context.getTextColorSecondary(): Int {
    return getColorFromAttr(android.R.attr.textColorSecondary)
}

@DrawableRes
fun Context.getSelectableItemBackground(): Int {
    return getDrawableFromAttr(android.R.attr.selectableItemBackground)
}

@DrawableRes
fun Context.getSelectableItemBackgroundBorderless(): Int {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        return getDrawableFromAttr(android.R.attr.selectableItemBackgroundBorderless)
    } else {
        return getDrawableFromAttr(android.R.attr.selectableItemBackground)
    }
}

@ColorInt
fun Context.getColorFromAttr(@AttrRes attributeColor: Int): Int {
    val value = TypedValue()
    theme.resolveAttribute(attributeColor, value, true)
    return value.data
}

@ColorInt
fun Context.getColorFromRes(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

@DrawableRes
fun Context.getDrawableFromAttr(@AttrRes attributeDrawable: Int): Int {
    val value = TypedValue()
    theme.resolveAttribute(attributeDrawable, value, true)
    return value.resourceId
}

fun Context.getString(text: String? = null, textRes: Int? = null): String? {
    when {
        text == null && textRes == null -> return null
        text != null -> return text
        textRes != null && textRes != 0 -> return getString(textRes)
        else -> return null
    }
}

fun Context.getStringNullable(stringRes: Int?): String? {
    if (stringRes == null) {
        return null
    } else {
        return getString(stringRes)
    }
}

fun Context.getStringResByName(resName: String): String? {
    try {
        return getString(resources.getIdentifier(resName, "string", packageName))
    } catch (e: Exception) {
        return null
    }
}

fun Context.getStringResByName(resName: String?, formatArgs: Array<String>?): String? {
    try {
        return getString(resources.getIdentifier(resName, "string", packageName), formatArgs)
    } catch (e: Exception) {
        return null
    }
}

fun Context.getActivity(): AppCompatActivity? {
    if (this is AppCompatActivity) {
        return this
    } else if (this is ContextWrapper) {
        return this.baseContext.getActivity()
    } else {
        return null
    }
}

fun Context.loadAnimation(@AnimRes id: Int): Animation = AnimationUtils.loadAnimation(applicationContext, id)

fun Context.showSoftKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun Context.hideSoftKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
