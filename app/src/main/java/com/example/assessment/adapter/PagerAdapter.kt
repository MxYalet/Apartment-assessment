package com.example.assessment.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.assessment.activity.MainActivity
import com.example.assessment.fragment.ActivityFragment
import com.example.assessment.fragment.LockerActivity
import com.example.assessment.fragment.LockerFragment
import com.example.assessment.fragment.MainFragment

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ActivityFragment.newInstance(MainActivity::class.java)
            1 -> ActivityFragment.newInstance(LockerActivity::class.java)

            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Home"
            1 -> "Locker"
            2 -> "Rent"
            3 -> "Advert"
            4 -> "Account"

            else -> null
        }
    }
}
