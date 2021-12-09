package com.aemerse.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aemerse.onboard.OnboardAdvanced
import com.aemerse.onboard.OnboardFragment
import com.aemerse.onboard.OnboardPageTransformerType
import com.aemerse.onboard.model.SliderPage

class IntroActivity : OnboardAdvanced() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(
            OnboardFragment.newInstance(
            "Welcome!",
            "Ever wondered what was missing in your life?",
            resourceId = R.raw.teen,
            titleTypefaceFontRes = R.font.lato,
            descriptionTypefaceFontRes = R.font.lato,
                backgroundDrawable = R.drawable.back_slide2,
                isLottie = true
        ))

        addSlide(OnboardFragment.newInstance(
            SliderPage(
            "Tired of Searching?",
            "Congratulations, you are at the right place!",
            resourceId = R.raw.location,
            backgroundDrawable = R.drawable.back_slide1,
            titleTypeface = "OpenSans-Light.ttf",
            descriptionTypeface = "OpenSans-Light.ttf",
                isLottie = true
        )
        ))

        addSlide(OnboardFragment.newInstance(
            "Meet, Code and Discuss",
            "Connect with the community of fierce developers!",
            resourceId = R.raw.rocket,
            backgroundDrawable = R.drawable.back_slide3,
            titleTypefaceFontRes = R.font.opensans_regular,
            descriptionTypefaceFontRes = R.font.opensans_regular,
            isLottie = true
        ))

        addSlide(OnboardFragment.newInstance(
            "Explore and Learn",
            "Contribute to the community and build your reputation!",
            resourceId = R.raw.trophy,
            backgroundDrawable = R.drawable.back_slide4,
            isLottie = true
        ))

        addSlide(OnboardFragment.newInstance(
            "Are you ready?",
            "Join us, support us and become a part of us!",
            resourceId = R.drawable.metabrainz,
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