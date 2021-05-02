package com.alphelios.onboarder

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alphelios.onboarder.databinding.ActivityOnboarderBinding
import java.util.ArrayList

class OnboarderActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var colors: Array<Int>
    private var evaluator: ArgbEvaluator? = null
    private var shouldDarkenButtonsLayout = false
    private var shouldUseFloatingActionButton = false
    private var onboarderAdapter: OnboarderAdapter? = null
    private lateinit var binding: ActivityOnboarderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboarderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideActionBar()

        binding.vpOnboarderPager.addOnPageChangeListener(object: OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (position < onboarderAdapter!!.count - 1 && position < colors.size - 1) {
                    binding.vpOnboarderPager.setBackgroundColor((evaluator!!.evaluate(positionOffset, colors[position], colors[position + 1]) as Int))
                    if (shouldDarkenButtonsLayout) {
                        binding.buttonsLayout.setBackgroundColor(darkenColor(evaluator!!.evaluate(positionOffset, colors[position], colors[position + 1]) as Int))
                        binding.divider.visibility = View.GONE
                    }
                } else {
                    binding.vpOnboarderPager.setBackgroundColor(colors[colors.size - 1])
                    if (shouldDarkenButtonsLayout) {
                        binding.buttonsLayout.setBackgroundColor(darkenColor(colors[colors.size - 1]))
                        binding.divider.visibility = View.GONE
                    }
                }
            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}
        })
        binding.ibNext.setOnClickListener(this)
        binding.btnSkip.setOnClickListener(this)
        binding.btnFinish.setOnClickListener(this)
        binding.fab.setOnClickListener(this)
        evaluator = ArgbEvaluator()
    }

    fun setOnboardPagesReady(pages: List<OnboarderPage>) {
        onboarderAdapter = OnboarderAdapter(pages, supportFragmentManager)
        binding.vpOnboarderPager.adapter = onboarderAdapter
        colors = getPageBackgroundColors(this, pages)
        binding.circleIndicatorView.attachViewPager(binding.vpOnboarderPager)
    }

//    fun setInactiveIndicatorColor(color: Int) {
//        binding.circleIndicatorView.setInactiveIndicatorColor(color)
//    }
//
//    fun setActiveIndicatorColor(color: Int) {
//        binding.circleIndicatorView.setActiveIndicatorColor(color)
//    }

    fun shouldDarkenButtonsLayout(shouldDarkenButtonsLayout: Boolean) {
        this.shouldDarkenButtonsLayout = shouldDarkenButtonsLayout
    }

    fun setDividerColor(@ColorInt color: Int) {
        if (!shouldDarkenButtonsLayout) binding.divider.setBackgroundColor(color)
    }

    fun setDividerHeight(heightInDp: Int) {
        if (!shouldDarkenButtonsLayout) binding.divider.layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightInDp.toFloat(),
                resources.displayMetrics).toInt()
    }

    fun setDividerVisibility(dividerVisibility: Int) {
        binding.divider.visibility = dividerVisibility
    }

    fun setSkipButtonTitle(title: CharSequence?) {
        binding.btnSkip.text = title
    }

    fun setSkipButtonHidden() {
        binding.btnSkip.visibility = View.GONE
    }

    fun setSkipButtonTitle(@StringRes titleResId: Int) {
        binding.btnSkip.setText(titleResId)
    }

    fun setFinishButtonTitle(title: CharSequence?) {
        binding.btnFinish.text = title
    }

    fun setFinishButtonTitle(@StringRes titleResId: Int) {
        binding.btnFinish.setText(titleResId)
    }

    fun shouldUseFloatingActionButton(shouldUseFloatingActionButton: Boolean) {
        this.shouldUseFloatingActionButton = shouldUseFloatingActionButton
        if (shouldUseFloatingActionButton) {
            binding.fab.visibility = View.VISIBLE
            setDividerVisibility(View.GONE)
            shouldDarkenButtonsLayout(false)
            binding.btnFinish.visibility = View.GONE
            binding.btnSkip.visibility = View.GONE
            binding.ibNext.visibility = View.GONE
            binding.ibNext.isFocusable = false
            binding.buttonsLayout.layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 96f,
                    resources.displayMetrics).toInt()
        }
    }

    private fun darkenColor(@ColorInt color: Int): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= 0.9f
        return Color.HSVToColor(hsv)
    }

    override fun onClick(v: View) {
        val i = v.id
        val isInLastPage = binding.vpOnboarderPager.currentItem == onboarderAdapter!!.count - 1
        when {
            i == R.id.ib_next || i == R.id.fab && !isInLastPage -> {
                binding.vpOnboarderPager.currentItem = binding.vpOnboarderPager.currentItem + 1
            }
            i == R.id.btn_skip -> {
                onSkipButtonPressed()
            }
            i == R.id.btn_finish || i == R.id.fab && isInLastPage -> {
                onFinishButtonPressed()
            }
        }
    }

    private fun hideActionBar() {
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
    }

    fun onSkipButtonPressed() {
        binding.vpOnboarderPager.currentItem = onboarderAdapter!!.count
    }

    fun onFinishButtonPressed(){

    }

    fun getPageBackgroundColors(context: Context?, pages: List<OnboarderPage>): Array<Int> {
        val colorsList: MutableList<Int> = ArrayList()
        for (page in pages) {
            colorsList.add(ContextCompat.getColor(context!!, page.backgroundColor))
        }
        return colorsList.toTypedArray()
    }
}