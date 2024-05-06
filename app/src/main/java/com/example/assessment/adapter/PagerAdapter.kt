package com.example.assessment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.assessment.fragment.LockerFragment
import com.example.assessment.fragment.MainFragment

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        // Return fragment instance based on position
        return when (position) {
            0 -> MainFragment()
            1 -> LockerFragment()

            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getCount(): Int {
        // Return the number of tabs
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        // Return tab titles
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
