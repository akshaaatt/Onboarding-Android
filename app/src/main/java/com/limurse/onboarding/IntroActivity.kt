package com.limurse.onboarding

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.limurse.onboard.OnboardAdvanced
import com.limurse.onboard.OnboardFragment
import com.limurse.onboard.OnboardPageTransformerType
import com.limurse.onboard.model.SliderPage

class IntroActivity : OnboardAdvanced() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSignInButton(false)
        isWizardMode = true
        val permissions = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                listOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.POST_NOTIFICATIONS
                )
            }
            else -> {
                listOf(android.Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }

        askForPermissions(
            permissions = permissions.toTypedArray(),
            slideNumber = 2,
            required = true
        )

        addSlide(OnboardFragment.newInstance(
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

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        Toast.makeText(applicationContext, "Skip pressed", Toast.LENGTH_SHORT).show()
    }

    override fun onSignInPressed(currentFragment: Fragment?) {
        super.onSignInPressed(currentFragment)
        Toast.makeText(applicationContext, "Sign In pressed", Toast.LENGTH_SHORT).show()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        Toast.makeText(applicationContext, "Done pressed", Toast.LENGTH_SHORT).show()
    }
}