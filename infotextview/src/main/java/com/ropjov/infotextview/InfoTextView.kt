package com.ropjov.infotextview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt

class InfoTextView : LinearLayout {

    // components
    private lateinit var view: View
    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var textTextView: TextView

    // data
    private var title: String = ""
    private var text: String = ""
    private var iconResource: Int = -1

    // colors
    private var titleColor: Int = -1
    private var textColor: Int = -1
    private var iconTint: Int = -1

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init(context, attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    @SuppressLint("ResourceAsColor")
    private fun init(context: Context?, attrs: AttributeSet?) {
        context!!.theme.obtainStyledAttributes(
            attrs,
            R.styleable.InfoTextView,
            0,
            0
        ).apply {
            try {
                title = getString(R.styleable.InfoTextView_title) ?: ""
                text = getString(R.styleable.InfoTextView_text) ?: ""
                iconResource = getResourceId(R.styleable.InfoTextView_icon, -1)

                iconTint = getColor(
                    R.styleable.InfoTextView_iconTint,
                    resources.getColor(android.R.color.tertiary_text_light)
                )
                titleColor = getColor(
                    R.styleable.InfoTextView_titleColor,
                    resources.getColor(android.R.color.primary_text_light)
                )
                textColor = getColor(
                    R.styleable.InfoTextView_textColor,
                    resources.getColor(android.R.color.tertiary_text_light)
                )
            } finally {
                recycle()
            }
        }

        view = View.inflate(context, R.layout.info_text_view_layout, this)
        imageView = view.findViewById(R.id.icon)
        titleTextView = view.findViewById(R.id.header)
        textTextView = view.findViewById(R.id.content)

        loadColors()
        render()
    }

    private fun loadColors() {
        titleTextView.setTextColor(titleColor)
        textTextView.setTextColor(textColor)
        imageView.imageTintList = ColorStateList.valueOf(iconTint)
    }

    @SuppressLint("ResourceAsColor")
    private fun render() {
        if (iconResource != -1) {
            imageView.setImageResource(iconResource)
        } else {
            imageView.setImageBitmap(null)
        }

        titleTextView.text = title
        textTextView.text = text

        if (text == "") {
            textTextView.visibility = View.GONE
            titleTextView.gravity = Gravity.CENTER_VERTICAL
        } else {
            textTextView.visibility = View.VISIBLE
            titleTextView.gravity = Gravity.BOTTOM
        }
    }

    /*
     * PUBLIC FUNCTIONS
     */

    fun setOnClickListener(callback: (Unit) -> Unit) {
        view.setOnClickListener {
            callback.invoke(Unit)
        }
    }

    fun setHeader(text: String) {
        title = text
        render()
    }

    fun setText(text: String) {
        this.text = text
        render()
    }

    fun setIcon(resourceId: Int) {
        iconResource = resourceId
        render()
    }

    fun setTitleColor(@ColorInt color: Int) {
        titleColor = color
        loadColors()
    }

    fun setTextColor(@ColorInt color: Int) {
        textColor = color
        loadColors()
    }

    fun setIconTint(@ColorInt color: Int) {
        iconTint = color
        loadColors()
    }
}
