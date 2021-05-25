package com.limerse.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.limerse.onboard.OnboardFragment
import com.limerse.onboard.OnboardPageTransformerType
import com.limerse.onboard.model.SliderPage

class IntroActivity : com.limerse.onboard.Onboard2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(
            OnboardFragment.newInstance(
            "Welcome!",
            "This is a demo of the Onboard library, using the second layout.",
            imageDrawable = R.drawable.ic_slide1,
            backgroundDrawable = R.drawable.back_slide1,
            titleTypefaceFontRes = R.font.lato,
            descriptionTypefaceFontRes = R.font.lato
        ))

        addSlide(OnboardFragment.newInstance(
            SliderPage(
            "Gradients!",
            "This text is written on a gradient background",
            imageDrawable = R.drawable.ic_slide2,
            backgroundDrawable = R.drawable.back_slide2,
            titleTypeface = "OpenSans-Light.ttf",
            descriptionTypeface = "OpenSans-Light.ttf"
        )
        ))

        addSlide(OnboardFragment.newInstance(
            "Simple, yet Customizable",
            "The library offers a lot of customization, while keeping it simple for those that like simple.",
            imageDrawable = R.drawable.ic_slide3,
            backgroundDrawable = R.drawable.back_slide3,
            titleTypefaceFontRes = R.font.opensans_regular,
            descriptionTypefaceFontRes = R.font.opensans_regular
        ))

        addSlide(OnboardFragment.newInstance(
            "Explore",
            "Feel free to explore the rest of the library demo!",
            imageDrawable = R.drawable.ic_slide4,
            backgroundDrawable = R.drawable.back_slide4
        ))

        addSlide(OnboardFragment.newInstance(
            ":)",
            "...gradients are awesome!",
            imageDrawable = R.mipmap.ic_launcher,
            backgroundDrawable = R.drawable.back_slide5
        ))


        setTransformer(OnboardPageTransformerType.Parallax())
    }

    public override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }
}