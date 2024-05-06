package com.example.assessment.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.assessment.R
import com.example.assessment.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.bottomTab)

        val adapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.setIcon(R.drawable.icon_home)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.locker_icon)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.padlock)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.advert_icon)
        tabLayout.getTabAt(4)?.setIcon(R.drawable.profile_icon)
    }

    // Method to navigate to a specific fragment
    fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.viewPager, fragment)
            .addToBackStack(null)
            .commit()
    }
    }
