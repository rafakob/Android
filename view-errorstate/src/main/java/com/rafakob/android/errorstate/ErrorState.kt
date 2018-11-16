package com.rafakob.android.errorstate

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

class ErrorState : LinearLayout {
    private var iconView: ImageView? = null
    private var messageView: TextView? = null
    private var actionView: Button? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setup(context, attrs)
    }

    private fun setup(context: Context, attrs: AttributeSet?) {
        LinearLayout.inflate(context, R.layout.layout_error_state, this)
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER

        iconView = findViewById(R.id.errorStateIcon)
        messageView = findViewById(R.id.errorStateMessage)
        actionView = findViewById(R.id.errorStateAction)

        attrs?.let {
            context.theme.obtainStyledAttributes(attrs, R.styleable.ErrorState, 0, 0).apply {

                if (hasValue(R.styleable.ErrorState_errorMessage)) {
                    setMessage(getString(R.styleable.ErrorState_errorMessage))
                }
                if (hasValue(R.styleable.ErrorState_errorMessageColor)) {
                    messageView?.setTextColor(getColor(R.styleable.ErrorState_errorMessageColor, 0))
                }
                if (hasValue(R.styleable.ErrorState_errorAction)) {
                    setAction(getString(R.styleable.ErrorState_errorAction))
                }
                if (hasValue(R.styleable.ErrorState_errorIcon)) {
                    setIcon(getDrawable(R.styleable.ErrorState_errorIcon))
                }
                if (hasValue(R.styleable.ErrorState_errorIconTint)) {
                    iconView?.setColorFilter(getColor(R.styleable.ErrorState_errorIconTint, 0), PorterDuff.Mode.SRC_IN)
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
