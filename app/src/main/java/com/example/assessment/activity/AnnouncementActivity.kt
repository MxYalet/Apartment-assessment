package com.example.assessment.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.assessment.R

class AnnouncementActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_announcement)


        val closeButton: ImageButton = findViewById(R.id.close_btn)
        closeButton.setOnClickListener {
            finish()
        }
    }
    }

