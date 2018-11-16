package com.rafakob.android.floatingedittext

import android.content.Context
import android.graphics.Typeface
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.text.InputFilter
import android.util.AttributeSet
import android.util.TypedValue

class FloatingEditText : TextInputLayout {

    lateinit var editText: TextInputEditText
    var text: String?
        set(value) {
            editText.setText(value)
        }
        get() {
            return editText.text.toString()
        }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setup(context, attrs)
    }

    private fun setup(context: Context, attrs: AttributeSet?) {
        editText = TextInputEditText(context)

        attrs?.let {
            context.theme.obtainStyledAttributes(it, R.styleable.FloatingEditText, 0, 0).apply {

                if (hasValue(R.styleable.FloatingEditText_android_text)) {
                    editText.setText(getString(R.styleable.FloatingEditText_android_text))
                }

                if (hasValue(R.styleable.FloatingEditText_android_textAllCaps)) {
                    editText.setAllCaps(getBoolean(R.styleable.FloatingEditText_android_textAllCaps, false))
                }

                if (hasValue(R.styleable.FloatingEditText_android_textColor)) {
                    editText.setTextColor(getColor(R.styleable.FloatingEditText_android_textColor, 0))
                }

                if (hasValue(R.styleable.FloatingEditText_android_textSize)) {
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getDimensionPixelSize(R.styleable.FloatingEditText_android_textSize, 0).toFloat())
                }

                var textStyle = Typeface.NORMAL
                if (hasValue(R.styleable.FloatingEditText_android_textStyle)) {
                    textStyle = getInt(R.styleable.FloatingEditText_android_textStyle, Typeface.NORMAL)
                }

                var fontFamily: String? = "sans-serif"
                if (hasValue(R.styleable.FloatingEditText_android_fontFamily)) {
                    fontFamily = getString(R.styleable.FloatingEditText_android_fontFamily)
                }

                editText.typeface = Typeface.create(fontFamily, textStyle)

                if (hasValue(R.styleable.FloatingEditText_android_inputType)) {
                    editText.inputType = getInt(R.styleable.FloatingEditText_android_inputType, -1)
                }

                if (hasValue(R.styleable.FloatingEditText_android_imeOptions)) {
                    editText.imeOptions = getInt(R.styleable.FloatingEditText_android_imeOptions, -1)
                }

                if (hasValue(R.styleable.FloatingEditText_android_maxLength)) {
                    editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(getInt(R.styleable.FloatingEditText_android_maxLength, 0)))
                }

                if (hasValue(R.styleable.FloatingEditText_android_maxLines)) {
                    editText.maxLines = getInt(R.styleable.FloatingEditText_android_maxLines, 1)
                }

                recycle()
            }
        }

        addView(editText)
    }
}
