package com.alphelios.onboarder

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class OnboarderPage @JvmOverloads constructor(
        var title: String? = null,
        var description: String? = null,
        @StringRes var titleResourceId: Int = 0,
        @StringRes var descriptionResourceId: Int = 0,
        @ColorRes var titleColor: Int = 0,
        @ColorRes var descriptionColor: Int = 0,
        @DrawableRes var resourceId: Int = 0,
        var resource: Drawable? = null,
        var isLottie: Boolean = false,
        @ColorRes var backgroundColor: Int  = R.color.black_transparent,
        var titleTextSize: Float = 0f,
        var descriptionTextSize: Float = 0f,
        var isMultilineDescriptionCentered: Boolean = false,
        @ColorRes var activeIndicatorColor: Int = R.color.active_indicator
)