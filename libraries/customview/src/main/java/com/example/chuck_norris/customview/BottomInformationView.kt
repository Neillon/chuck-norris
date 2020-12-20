package com.example.chuck_norris.customview

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.chuck_norris.customview.delegates.CustomViewProperty
import timber.log.Timber

class BottomInformationView(
    context: Context,
    private val attributeSet: AttributeSet
) : ConstraintLayout(context, attributeSet) {

    private var informationBackgroundColor: Int by CustomViewProperty(Color.WHITE)

    //        set(value) {
//            field = value
//            invalidate()
//        }
    var textColor: Int by CustomViewProperty(Color.BLACK)

    //        set(value) {
//            field = value
//            invalidate()
//        }
    var information: String by CustomViewProperty("")

    //        set(value) {
//            field = value
//            invalidate()
//        }
    var isLoading: Boolean by CustomViewProperty(false)
//        set(value) {
//            field = value
//            invalidate()
//        }

    private val progressBarBottomInformation: ProgressBar by lazy { findViewById(R.id.progressBarBottomInformation) }
    private val textViewBottomInformation: TextView by lazy { findViewById(R.id.textViewBottomInformation) }

    init {
        inflate(context, R.layout.bottom_information_view, this)
        context.obtainStyledAttributes(attributeSet, R.styleable.BottomInformationView)
            .apply {
                setupBackgroundColor()
                setupTextInformation()
                setupProgressBar()
            }.recycle()

//        animateOnEntry()
    }

    /**
     * Animate on entry sliding up the view
     */
//    private fun animateOnEntry() {
//        ObjectAnimator.ofFloat(
//            this@BottomInformationView,
//            "translationY",
//            100f
//        ).apply {
//            duration = 1000
//
//            addUpdateListener {
//                Timber.i("Update to slide the view")
//                invalidate()
//            }
//
//            start()
//        }
//    }

    /**
     * Setup background color of the view
     */
    private fun TypedArray.setupBackgroundColor() {
        if (hasValue(R.styleable.BottomInformationView_information_background_color)) {
            informationBackgroundColor = getColor(
                R.styleable.BottomInformationView_information_background_color,
                Color.WHITE
            )
            background = ColorDrawable(informationBackgroundColor)
        }
    }

    /**
     * Setup information text for loading
     */
    private fun TypedArray.setupTextInformation() {
        if (hasValue(R.styleable.BottomInformationView_information_text_color)) {
            textColor = getColor(
                R.styleable.BottomInformationView_information_text_color,
                Color.BLACK
            )
            textViewBottomInformation.setTextColor(textColor)
        }

        if (hasValue(R.styleable.BottomInformationView_information)) {
            information = getString(R.styleable.BottomInformationView_information).toString()
            textViewBottomInformation.text = information
        }
    }

    /**
     * Setup progressBar for loading state
     */
    private fun TypedArray.setupProgressBar() {
        if (hasValue(R.styleable.BottomInformationView_isLoading_Information)) {
            isLoading = getBoolean(R.styleable.BottomInformationView_isLoading_Information, false)
            progressBarBottomInformation.progressTintList = ColorStateList.valueOf(textColor)
            progressBarBottomInformation.isVisible = isLoading
        }
    }

}