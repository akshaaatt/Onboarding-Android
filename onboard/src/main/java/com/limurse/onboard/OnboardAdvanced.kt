package com.limurse.onboard

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageButton
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.common.SignInButton

abstract class OnboardAdvanced : OnboardBase() {

    override val layoutId = R.layout.onboard_intro_layout2

    @IdRes
    var backgroundResource: Int? = null
        set(value) {
            field = value
            if (field != null) {
                field?.let { backgroundFrame.setBackgroundResource(it) }
            }
        }

    var backgroundDrawable: Drawable? = null
        set(value) {
            field = value
            if (field != null) {
                backgroundFrame.background = field
            }
        }

    private lateinit var backgroundFrame: ConstraintLayout
    private lateinit var bottomBar: View
    private lateinit var skipImageButton: ImageButton
    private lateinit var googleSignInButton: SignInButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backgroundFrame = findViewById(R.id.background)
        bottomBar = findViewById(R.id.bottom)
        skipImageButton = findViewById(R.id.skip)
        googleSignInButton = findViewById(R.id.sign_in_button)
        if (isRtl) {
            skipImageButton.scaleX = -1F
        }
    }

    /**
     * Override viewpager bar color
     * @param color your color resource
     */
    fun setBarColor(@ColorInt color: Int) {
        bottomBar.setBackgroundColor(color)
    }

    fun setSignInButton(show: Boolean){
        when {
            show -> {
                googleSignInButton.visibility = VISIBLE
            }
            else -> {
                googleSignInButton.visibility = GONE
            }
        }
    }

    /**
     * Override Skip button drawable
     * @param imageSkipButton your drawable resource
     */
    fun setImageSkipButton(imageSkipButton: Drawable) {
        skipImageButton.setImageDrawable(imageSkipButton)
    }

    /**
     * Override next button arrow color
     *
     * @param color your color
     */
    fun setNextArrowColor(@ColorInt color: Int) {
        val nextButton = findViewById<ImageButton>(R.id.next)
        nextButton.setColorFilter(color)
    }

    /**
     * Override skip button color
     *
     * @param colorSkipButton your color resource
     */
    fun setSkipArrowColor(@ColorInt colorSkipButton: Int) {
        val skip = findViewById<ImageButton>(R.id.skip)
        skip.setColorFilter(colorSkipButton)
    }
}
