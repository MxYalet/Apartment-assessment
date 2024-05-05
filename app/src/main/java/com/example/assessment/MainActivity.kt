package com.example.assessment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomTab: TabLayout = findViewById(R.id.bottomTab)

        bottomTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {

                    }
                    1 ->{
                        //Explore Tab Selected
                        //    val intent = Intent(this@MainActivity, LockerActivity::class.java)
                        //  startActivity(intent)
                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Change the color of the unselected tab back to its original color
                tab?.let {
                    it.view.setBackgroundColor(resources.getColor(android.R.color.transparent))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Not needed for this implementation
            }
        })
    }
}