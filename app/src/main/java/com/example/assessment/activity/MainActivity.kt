package com.example.assessment.activity

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.assessment.R
import com.example.assessment.adapter.PagerAdapter
import com.example.assessment.fragment.LockerFragment
import com.example.assessment.fragment.MainFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.bottomTab)

        tabLayout.getTabAt(0)?.setIcon(R.drawable.icon_home)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.locker_icon)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.padlock)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.advert_icon)
        tabLayout.getTabAt(4)?.setIcon(R.drawable.profile_icon)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    it.icon?.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.bg_header), PorterDuff.Mode.SRC_IN)
                    when (it.position) {

                        0 -> {
                            it.icon?.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.bg_header), PorterDuff.Mode.SRC_IN)
                            navigateToFragment(MainFragment())
                        }
                        1 -> {
                            it.icon?.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.bg_header), PorterDuff.Mode.SRC_IN)
                            navigateToFragment(LockerFragment())
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        navigateToFragment(MainFragment())
    }

    fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
    }
