package com.aemerse.onboard

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import com.aemerse.onboard.model.SliderPage

@Suppress("LongParameterList")
class OnboardFragment : OnboardBaseFragment() {

    override val layoutId: Int get() = R.layout.onboard_fragment_intro

    companion object {

        /**
         * Generates a new instance for [OnboardFragment]
         *
         * @param title CharSequence which will be the slide title
         * @param description CharSequence which will be the slide description
         * @param resourceId @DrawableRes (Integer) the image that will be
         *                             displayed, obtained from Resources
         * @param backgroundColor @ColorInt (Integer) custom background color
         * @param titleColor @ColorInt (Integer) custom title color
         * @param descriptionColor @ColorInt (Integer) custom description color
         * @param titleTypefaceFontRes @FontRes (Integer) custom title typeface obtained
         *                             from Resources
         * @param descriptionTypefaceFontRes @FontRes (Integer) custom description typeface obtained
         *                             from Resources
         * @param backgroundDrawable @DrawableRes (Integer) custom background drawable
         *
         * @return An [OnboardFragment] created instance
         */
        @JvmOverloads
        @JvmStatic
        fun newInstance(
            title: CharSequence? = null,
            description: CharSequence? = null,
            resourceId: Int = 0,
            @ColorInt backgroundColor: Int = 0,
            @ColorInt titleColor: Int = 0,
            @ColorInt descriptionColor: Int = 0,
            @FontRes titleTypefaceFontRes: Int = 0,
            @FontRes descriptionTypefaceFontRes: Int = 0,
            @DrawableRes backgroundDrawable: Int = 0,
            isLottie: Boolean = false
        ): OnboardFragment {
            return newInstance(
                SliderPage(
                    title = title,
                    description = description,
                    resourceId = resourceId,
                    backgroundColor = backgroundColor,
                    titleColor = titleColor,
                    descriptionColor = descriptionColor,
                    titleTypefaceFontRes = titleTypefaceFontRes,
                    descriptionTypefaceFontRes = descriptionTypefaceFontRes,
                    backgroundDrawable = backgroundDrawable,
                    isLottie = isLottie
                )
            )
        }

        /**
         * Generates an [OnboardFragment] from a given [SliderPage]
         *
         * @param sliderPage the [SliderPage] object which contains all attributes for
         * the current slide
         *
         * @return An [OnboardFragment] created instance
         */
        @JvmStatic
        fun newInstance(sliderPage: SliderPage): OnboardFragment {
            val slide = OnboardFragment()
            slide.arguments = sliderPage.toBundle()
            return slide
        }
    }
}
