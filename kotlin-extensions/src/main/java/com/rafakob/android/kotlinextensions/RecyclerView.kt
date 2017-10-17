package com.rafakob.android.kotlinextensions

import android.support.v7.widget.*

fun RecyclerView.init(adapter: RecyclerView.Adapter<*>,
                      animator: SimpleItemAnimator? = DefaultItemAnimator(),
                      manager: RecyclerView.LayoutManager = LinearLayoutManager(context)) {

    init(adapter, animator, manager, null)
}

fun RecyclerView.init(adapter: RecyclerView.Adapter<*>,
                      animator: SimpleItemAnimator? = DefaultItemAnimator(),
                      divider: Int,
                      manager: RecyclerView.LayoutManager = LinearLayoutManager(context)) {

    init(adapter, animator, manager, DividerItemDecoration(context, divider))
}

fun RecyclerView.init(adapter: RecyclerView.Adapter<*>,
                      animator: SimpleItemAnimator? = DefaultItemAnimator(),
                      manager: RecyclerView.LayoutManager = LinearLayoutManager(context),
                      vararg decorators: RecyclerView.ItemDecoration?) {

    this.layoutManager = manager
    this.itemAnimator = animator
    this.adapter = adapter

    decorators.forEach {
        it?.let { addItemDecoration(it) }
    }
}