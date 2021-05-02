package com.alphelios.onboarder

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alphelios.onboarder.OnboarderFragment.Companion.newInstance
import java.util.*

class OnboarderAdapter(pages: List<OnboarderPage>, fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var pages: List<OnboarderPage> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return newInstance(pages[position])
    }

    override fun getCount(): Int {
        return pages.size
    }

    init {
        this.pages = pages
    }
}