package com.alphelios.onboarding

import android.os.Bundle
import android.widget.Toast
import com.alphelios.onboarder.OnboarderActivity
import com.alphelios.onboarder.OnboarderPage
import java.util.*

class IntroActivity : OnboarderActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onboarderPage1 = OnboarderPage(
                "Planet Earth",
                "Our lovely pale blue dot",
                resourceId = R.raw.trophy,
                isLottie = true,
                activeIndicatorColor = R.color.colorAccent
        )

        val onboarderPage2 = OnboarderPage(
                title = "Venus",
                description = "The love goddess",
                resourceId = R.drawable.second
        )

        val onboarderPage3 = OnboarderPage(
                "Mars",
                "Say hi to Curiosity!",
                resourceId = R.drawable.third
        )

        onboarderPage1.backgroundColor = R.color.black
        onboarderPage2.backgroundColor = R.color.onboarder_bg_2
        onboarderPage3.backgroundColor = R.color.onboarder_bg_3

        val pages: MutableList<OnboarderPage> = ArrayList()
        pages.add(onboarderPage1)
        pages.add(onboarderPage2)
        pages.add(onboarderPage3)

        for (page in pages) {
            page.titleColor = R.color.primary_text
            page.descriptionColor = R.color.secondary_text
            page.isMultilineDescriptionCentered = true
        }
        setSkipButtonTitle("Skip")
        setFinishButtonTitle("Finish")
        setOnboardPagesReady(pages)
//        setActiveIndicatorColor(R.color.white)
//        setInactiveIndicatorColor(R.color.colorAccent)
    }

    public override fun onSkipButtonPressed() {
        super.onSkipButtonPressed()
        Toast.makeText(this, "Skip button was pressed!", Toast.LENGTH_SHORT).show()
    }

    override fun onFinishButtonPressed() {
        Toast.makeText(this, "Finish button was pressed", Toast.LENGTH_SHORT).show()
    }
}