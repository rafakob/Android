package com.rafakob.android.kotlinextensions

inline fun <T> with(obj: T, f: T.() -> Unit): T {
    obj.f()
    return obj
}

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}