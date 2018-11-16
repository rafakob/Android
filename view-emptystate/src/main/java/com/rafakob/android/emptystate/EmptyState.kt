package com.rafakob.android.emptystate

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class EmptyState : LinearLayout {
    private var iconView: ImageView? = null
    private var messageView: TextView? = null
    private var actionView: Button? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setup(context, attrs)
    }

    private fun setup(context: Context, attrs: AttributeSet?) {
        inflate(context, R.layout.layout_empty_state, this)
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER

        iconView = findViewById(R.id.emptyStateIcon)
        messageView = findViewById(R.id.emptyStateMessage)
        actionView = findViewById(R.id.emptyStateAction)

        attrs?.let {
            context.theme.obtainStyledAttributes(attrs, R.styleable.EmptyState, 0, 0).apply {

                if (hasValue(R.styleable.EmptyState_emptyMessage)) {
                    setMessage(getString(R.styleable.EmptyState_emptyMessage))
                }
                if (hasValue(R.styleable.EmptyState_emptyMessageColor)) {
                    messageView?.setTextColor(getColor(R.styleable.EmptyState_emptyMessageColor, 0))
                }
                if (hasValue(R.styleable.EmptyState_emptyAction)) {
                    setAction(getString(R.styleable.EmptyState_emptyAction))
                }
                if (hasValue(R.styleable.EmptyState_emptyIcon)) {
                    setIcon(getDrawable(R.styleable.EmptyState_emptyIcon))
                }
                if (hasValue(R.styleable.EmptyState_emptyIconTint)) {
                    iconView?.setColorFilter(getColor(R.styleable.EmptyState_emptyIconTint, 0), PorterDuff.Mode.SRC_IN)
                }

                recycle()
            }
        }
    }

    fun setIcon(@DrawableRes iconRes: Int) {
        iconView?.setImageResource(iconRes)
        iconView?.visibility = if (iconRes == 0) View.GONE else View.VISIBLE
    }

    fun setIcon(drawable: Drawable?) {
        iconView?.setImageDrawable(drawable)
        iconView?.visibility = if (drawable == null) View.GONE else View.VISIBLE
    }

    fun setMessage(message: String?) {
        messageView?.text = message
    }

    fun setMessage(@StringRes message: Int) {
        messageView?.setText(message)
    }

    fun setAction(action: String?) {
        actionView?.text = action
        actionView?.visibility = if (action.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    fun setAction(@StringRes action: Int) {
        setAction(resources.getString(action))
    }

    fun onActionClick(listener: (() -> Unit)?) {
        if (actionView == null) {
            return
        }
        if (listener != null) {
            actionView?.visibility = View.VISIBLE
            actionView?.setOnClickListener({ listener.invoke() })
        } else {
            actionView?.visibility = View.GONE
        }
    }
}
