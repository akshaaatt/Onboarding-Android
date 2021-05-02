package com.alphelios.onboarder

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView

class OnboarderFragment : Fragment() {
    private var onboarderTitle: String? = null
    private var onboarderDescription: String? = null
    private var onboarderIsLottie: Boolean = false

    @StringRes
    private var onboarderTitleResId = 0

    @ColorRes
    private var onboarderTitleColor = 0

    @StringRes
    private var onboarderDescriptionResId = 0

    @ColorRes
    private var onboarderDescriptionColor = 0

    private var onboarderResId = 0
    private var onboarderTitleTextSize = 0f
    private var onboarderDescriptionTextSize = 0f
    private var onboarderDescriptionTextCentered = false
    private var onboarderView: View? = null
    private var ivOnboarderImage: ImageView? = null
    private var ivOnboarderLottie: LottieAnimationView? = null
    private var tvOnboarderTitle: TextView? = null
    private var tvOnboarderDescription: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bundle = arguments
        onboarderTitle = bundle!!.getString(ONBOARDER_PAGE_TITLE, null)
        onboarderTitleResId = bundle.getInt(ONBOARDER_PAGE_TITLE_RES_ID, 0)
        onboarderTitleColor = bundle.getInt(ONBOARDER_PAGE_TITLE_COLOR, 0)
        onboarderTitleTextSize = bundle.getFloat(ONBOARDER_PAGE_TITLE_TEXT_SIZE, 0f)
        onboarderDescription = bundle.getString(ONBOARDER_PAGE_DESCRIPTION, null)
        onboarderDescriptionResId = bundle.getInt(ONBOARDER_PAGE_DESCRIPTION_RES_ID, 0)
        onboarderDescriptionColor = bundle.getInt(ONBOARDER_PAGE_DESCRIPTION_COLOR, 0)
        onboarderDescriptionTextSize = bundle.getFloat(ONBOARDER_PAGE_DESCRIPTION_TEXT_SIZE, 0f)
        onboarderDescriptionTextCentered = bundle.getBoolean(ONBOARDER_PAGE_DESCRIPTION_CENTERED, false)
        onboarderIsLottie = bundle.getBoolean(ONBOARDER_IS_LOTTIE, false)
        onboarderResId = bundle.getInt(ONBOARDER_PAGE_IMAGE_RES_ID, 0)

        onboarderView = inflater.inflate(R.layout.fragment_onboarder, container, false)
        ivOnboarderImage = onboarderView!!.findViewById<View>(R.id.iv_onboarder_image) as ImageView
        ivOnboarderLottie = onboarderView!!.findViewById<View>(R.id.iv_onboarder_lottie) as LottieAnimationView
        tvOnboarderTitle = onboarderView!!.findViewById<View>(R.id.tv_onboarder_title) as TextView
        tvOnboarderDescription = onboarderView!!.findViewById<View>(R.id.tv_onboarder_description) as TextView

        if (onboarderTitle != null) {
            tvOnboarderTitle!!.text = onboarderTitle
        }
        if (onboarderTitleResId != 0) {
            tvOnboarderTitle!!.text = resources.getString(onboarderTitleResId)
        }
        if (onboarderDescription != null) {
            tvOnboarderDescription!!.text = onboarderDescription
        }
        if (onboarderDescriptionResId != 0) {
            tvOnboarderDescription!!.text = resources.getString(onboarderDescriptionResId)
        }
        if (onboarderTitleColor != 0) {
            tvOnboarderTitle!!.setTextColor(ContextCompat.getColor(activity!!, onboarderTitleColor))
        }
        if (onboarderDescriptionColor != 0) {
            tvOnboarderDescription!!.setTextColor(ContextCompat.getColor(activity!!, onboarderDescriptionColor))
        }
        if (onboarderResId != 0) {
            if(onboarderIsLottie){
                ivOnboarderLottie!!.visibility = VISIBLE
                ivOnboarderImage!!.visibility = GONE
                ivOnboarderLottie!!.setAnimation(onboarderResId)
            }
            else{
                ivOnboarderImage!!.visibility = VISIBLE
                ivOnboarderLottie!!.visibility = GONE
                ivOnboarderImage!!.setImageDrawable(AppCompatResources.getDrawable(activity!!, onboarderResId))
            }
        }
        if (onboarderTitleTextSize != 0f) {
            tvOnboarderTitle!!.textSize = onboarderTitleTextSize
        }
        if (onboarderDescriptionTextSize != 0f) {
            tvOnboarderDescription!!.textSize = onboarderDescriptionTextSize
        }
        return onboarderView
    }

    override fun onResume() {
        super.onResume()
        tvOnboarderDescription!!.post {
            if (onboarderDescriptionTextCentered) {
                tvOnboarderDescription!!.gravity = Gravity.CENTER
            } else {
                tvOnboarderDescription!!.gravity = if (tvOnboarderDescription!!.lineCount > 1) Gravity.START else Gravity.CENTER
            }
        }
    }

    companion object {
        private const val ONBOARDER_PAGE_TITLE = "onboarder_page_title"
        private const val ONBOARDER_PAGE_TITLE_RES_ID = "onboarder_page_title_res_id"
        private const val ONBOARDER_PAGE_TITLE_COLOR = "onboarder_page_title_color"
        private const val ONBOARDER_PAGE_TITLE_TEXT_SIZE = "onboarder_page_title_text_size"
        private const val ONBOARDER_PAGE_DESCRIPTION = "onboarder_page_description"
        private const val ONBOARDER_PAGE_DESCRIPTION_RES_ID = "onboarder_page_description_res_id"
        private const val ONBOARDER_PAGE_DESCRIPTION_COLOR = "onborader_page_description_color"
        private const val ONBOARDER_PAGE_DESCRIPTION_TEXT_SIZE = "onboarder_page_description_text_size"
        private const val ONBOARDER_PAGE_DESCRIPTION_CENTERED = "onboarder_page_description_centered"
        private const val ONBOARDER_IS_LOTTIE = "onboarder_is_lottie"
        private const val ONBOARDER_PAGE_IMAGE_RES_ID = "onboarder_page_iamge_res_id"
        @JvmStatic
		fun newInstance(page: OnboarderPage): OnboarderFragment {
            val args = Bundle()
            args.putString(ONBOARDER_PAGE_TITLE, page.title)
            args.putString(ONBOARDER_PAGE_DESCRIPTION, page.description)
            args.putInt(ONBOARDER_PAGE_TITLE_RES_ID, page.titleResourceId)
            args.putInt(ONBOARDER_PAGE_DESCRIPTION_RES_ID, page.descriptionResourceId)
            args.putInt(ONBOARDER_PAGE_TITLE_COLOR, page.titleColor)
            args.putInt(ONBOARDER_PAGE_DESCRIPTION_COLOR, page.descriptionColor)
            args.putInt(ONBOARDER_PAGE_IMAGE_RES_ID, page.resourceId)
            args.putFloat(ONBOARDER_PAGE_TITLE_TEXT_SIZE, page.titleTextSize)
            args.putFloat(ONBOARDER_PAGE_DESCRIPTION_TEXT_SIZE, page.descriptionTextSize)
            args.putBoolean(ONBOARDER_PAGE_DESCRIPTION_CENTERED, page.isMultilineDescriptionCentered)
            args.putBoolean(ONBOARDER_IS_LOTTIE, page.isLottie)
            val fragment = OnboarderFragment()
            fragment.arguments = args
            return fragment
        }
    }
}