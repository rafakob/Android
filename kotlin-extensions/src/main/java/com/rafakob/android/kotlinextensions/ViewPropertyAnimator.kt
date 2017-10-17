package com.rafakob.android.kotlinextensions

import android.animation.Animator
import android.view.ViewPropertyAnimator

inline fun ViewPropertyAnimator.listener(init: AnimListener.() -> Unit): ViewPropertyAnimator = setListener(AnimListener().apply(init))

class AnimListener : Animator.AnimatorListener {
    private var _onAnimationStart: (() -> Unit)? = null
    private var _onAnimationEnd: (() -> Unit)? = null
    private var _onAnimationCancel: (() -> Unit)? = null
    private var _onAnimationRepeat: (() -> Unit)? = null

    override fun onAnimationStart(animator: Animator?) {
        _onAnimationStart?.invoke()
    }

    override fun onAnimationEnd(animator: Animator?) {
        _onAnimationEnd?.invoke()
    }

    override fun onAnimationCancel(animator: Animator?) {
        _onAnimationCancel?.invoke()
    }

    override fun onAnimationRepeat(animator: Animator?) {
        _onAnimationRepeat?.invoke()
    }

    fun onStart(f: () -> Unit) {
        _onAnimationStart = f
    }

    fun onEnd(f: () -> Unit) {
        _onAnimationEnd = f
    }

    fun onCancel(f: () -> Unit) {
        _onAnimationCancel = f
    }

    fun onRepeat(f: () -> Unit) {
        _onAnimationRepeat = f
    }
}