package com.limurse.onboard

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.limurse.onboard.internal.LogHelper
import com.limurse.onboard.internal.TypefaceContainer

internal const val ARG_TITLE = "title"
internal const val ARG_TITLE_TYPEFACE = "title_typeface"
internal const val ARG_TITLE_TYPEFACE_RES = "title_typeface_res"
internal const val ARG_DESC = "desc"
internal const val ARG_DESC_TYPEFACE = "desc_typeface"
internal const val ARG_DESC_TYPEFACE_RES = "desc_typeface_res"
internal const val ARG_DRAWABLE = "drawable"
internal const val ARG_BG_COLOR = "bg_color"
internal const val ARG_TITLE_COLOR = "title_color"
internal const val ARG_DESC_COLOR = "desc_color"
internal const val ARG_BG_DRAWABLE = "bg_drawable"
internal const val IS_LOTTIE = "is_lottie"

abstract class OnboardBaseFragment : Fragment(), SlideSelectionListener, SlideBackgroundColorHolder {

    private val logTAG = LogHelper.makeLogTag(OnboardBaseFragment::class.java)

    @get:LayoutRes
    protected abstract val layoutId: Int

    private var resourceId: Int = 0
    private var bgDrawable: Int = 0

    private var titleColor: Int = 0
    private var descColor: Int = 0
    final override var defaultBackgroundColor: Int = 0
        private set

    private var title: String? = null
    private var description: String? = null
    private var isLottie: Boolean = false
    private var titleTypeface: TypefaceContainer? = null
    private var descTypeface: TypefaceContainer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        val args = arguments
        if (args != null && args.size() != 0) {
            resourceId = args.getInt(ARG_DRAWABLE)
            title = args.getString(ARG_TITLE)
            description = args.getString(ARG_DESC)
            bgDrawable = args.getInt(ARG_BG_DRAWABLE)
            isLottie = args.getBoolean(IS_LOTTIE)

            val argsTitleTypeface = args.getString(ARG_TITLE_TYPEFACE)
            val argsDescTypeface = args.getString(ARG_DESC_TYPEFACE)
            val argsTitleTypefaceRes = args.getInt(ARG_TITLE_TYPEFACE_RES)
            val argsDescTypefaceRes = args.getInt(ARG_DESC_TYPEFACE_RES)
            titleTypeface = TypefaceContainer(argsTitleTypeface, argsTitleTypefaceRes)
            descTypeface = TypefaceContainer(argsDescTypeface, argsDescTypefaceRes)

            defaultBackgroundColor = args.getInt(ARG_BG_COLOR)
            titleColor = args.getInt(ARG_TITLE_COLOR, 0)
            descColor = args.getInt(ARG_DESC_COLOR, 0)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            resourceId = savedInstanceState.getInt(ARG_DRAWABLE)
            title = savedInstanceState.getString(ARG_TITLE)
            description = savedInstanceState.getString(ARG_DESC)
            isLottie = savedInstanceState.getBoolean(IS_LOTTIE)

            titleTypeface = TypefaceContainer(
                savedInstanceState.getString(ARG_TITLE_TYPEFACE),
                savedInstanceState.getInt(ARG_TITLE_TYPEFACE_RES, 0)
            )
            descTypeface = TypefaceContainer(
                savedInstanceState.getString(ARG_DESC_TYPEFACE),
                savedInstanceState.getInt(ARG_DESC_TYPEFACE_RES, 0)
            )

            defaultBackgroundColor = savedInstanceState.getInt(ARG_BG_COLOR)
            bgDrawable = savedInstanceState.getInt(ARG_BG_DRAWABLE)
            titleColor = savedInstanceState.getInt(ARG_TITLE_COLOR)
            descColor = savedInstanceState.getInt(ARG_DESC_COLOR)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutId, container, false)
        val titleText = view.findViewById<TextView>(R.id.title)
        val descriptionText = view.findViewById<TextView>(R.id.description)
        val slideImage = view.findViewById<ImageView>(R.id.image)
        val mainLayout = view.findViewById<ConstraintLayout>(R.id.main)
        val animationView = view.findViewById<LottieAnimationView>(R.id.animation_view)

        titleText.text = title
        descriptionText.text = description
        if (titleColor != 0) {
            titleText.setTextColor(titleColor)
        }
        if (descColor != 0) {
            descriptionText.setTextColor(descColor)
        }
        if(isLottie){
            slideImage.visibility = GONE
            animationView.visibility = VISIBLE
            animationView.setAnimation(resourceId)
        }
        else{
            slideImage.visibility = VISIBLE
            animationView.visibility = GONE
            slideImage.setImageResource(resourceId)
        }
        titleTypeface?.applyTo(titleText)
        descTypeface?.applyTo(descriptionText)

        if (bgDrawable != 0) {
            mainLayout?.setBackgroundResource(bgDrawable)
        } else {
            mainLayout?.setBackgroundColor(defaultBackgroundColor)
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        view?.findViewById<ImageView>(R.id.image).let {
            if (it is Animatable) {
                it.start()
            }
        }
    }

    override fun onPause() {
        super.onPause()

        view?.findViewById<ImageView>(R.id.image).let {
            if (it is Animatable) {
                it.start()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(ARG_DRAWABLE, resourceId)
        outState.putInt(ARG_BG_DRAWABLE, bgDrawable)
        outState.putString(ARG_TITLE, title)
        outState.putString(ARG_DESC, description)
        outState.putInt(ARG_BG_COLOR, defaultBackgroundColor)
        outState.putInt(ARG_TITLE_COLOR, titleColor)
        outState.putInt(ARG_DESC_COLOR, descColor)
        outState.putBoolean(IS_LOTTIE,isLottie)
        if (titleTypeface != null) {
            outState.putString(ARG_TITLE_TYPEFACE, titleTypeface?.typeFaceUrl)
            outState.putInt(ARG_TITLE_TYPEFACE_RES, titleTypeface?.typeFaceResource ?: 0)
        }
        if (descTypeface != null) {
            outState.putString(ARG_DESC_TYPEFACE, descTypeface?.typeFaceUrl)
            outState.putInt(ARG_DESC_TYPEFACE_RES, descTypeface?.typeFaceResource ?: 0)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onSlideDeselected() {
        LogHelper.d(logTAG, "Slide $title has been deselected.")
    }

    override fun onSlideSelected() {
        LogHelper.d(logTAG, "Slide $title has been selected.")
    }

    override fun setBackgroundColor(@ColorInt backgroundColor: Int) {
        view?.findViewById<ConstraintLayout>(R.id.main)?.setBackgroundColor(backgroundColor)
    }
}
