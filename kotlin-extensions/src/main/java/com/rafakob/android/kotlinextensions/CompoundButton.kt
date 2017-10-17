package com.rafakob.android.kotlinextensions

import android.widget.CompoundButton

inline fun CompoundButton.onCheckedChanged(crossinline f: (isChecked: Boolean) -> Unit) = setOnCheckedChangeListener { _, b -> f.invoke(b) }
