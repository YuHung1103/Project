package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class UserPage : AppCompatActivity() {

    private lateinit var FrontPageButton : ImageButton
    private lateinit var BookingButton: ImageButton
    private lateinit var ScanButton: ImageButton

    private lateinit var LogoutButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_layout)

        FrontPageButton = findViewById(R.id.front_page)
        BookingButton = findViewById(R.id.booking)
        ScanButton = findViewById(R.id.scan)

        FrontPageButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        BookingButton.setOnClickListener{
            val intent = Intent(this, BookingPage::class.java)
            startActivity(intent)
        }
        ScanButton.setOnClickListener{
            val intent = Intent(this, ScanPage::class.java)
            startActivity(intent)
        }

    }
}