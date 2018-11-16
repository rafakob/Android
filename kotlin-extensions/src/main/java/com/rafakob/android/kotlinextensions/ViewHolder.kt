package com.rafakob.android.kotlinextensions

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

val androidx.recyclerview.widget.RecyclerView.ViewHolder.context: Context
    get() = itemView.context
