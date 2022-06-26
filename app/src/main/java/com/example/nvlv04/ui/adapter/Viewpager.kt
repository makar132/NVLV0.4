package com.example.nvlv04.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.nvlv04.ui.fragments.OnboardingPageFragment
class Viewpager(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val total: Int = 3
    private val data : ArrayList<OnboardingPageFragment> = ArrayList(total)

    init {
        for (i in 0 until total) {
            val fragment = OnboardingPageFragment()
            val bundle = Bundle()
            bundle.putInt("POSITION", i+1)
            fragment.arguments = bundle
            data.add(fragment)
        }
    }


    override fun getItem(position: Int): Fragment {
        return data[position]
    }

    override fun getCount(): Int {
        return total
    }
}
