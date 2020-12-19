package com.example.chuck_norris.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class TopTitle(
    context: Context,
    private val attributeSet: AttributeSet
): ConstraintLayout(context, attributeSet) {

    private var title: String = ""
    private val textViewTitle: TextView by lazy { findViewById(R.id.textViewTitle) }

    init {
        inflate(context, R.layout.top_title, this)

        setupTitle()
    }

    /**
     * Setup Title of the textViewTitle using attributSet
     */
    private fun setupTitle() {
        var attrs = context.obtainStyledAttributes(attributeSet, R.styleable.TopTitle).apply {
            if (hasValue(R.styleable.TopTitle_title_text)) {
                title = getString(R.styleable.TopTitle_title_text).toString()
                textViewTitle.text = title
            }
        }
        attrs.recycle()
    }

}