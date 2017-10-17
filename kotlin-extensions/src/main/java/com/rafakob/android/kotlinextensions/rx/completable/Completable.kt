package com.rafakob.android.kotlinextensions.rx.completable

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Completable.io(): Completable {
    return subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
}

fun Completable.computation(): Completable {
    return subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
}