package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class UserPage : AppCompatActivity() {

    private lateinit var FrontPageButton : ImageButton

    private lateinit var ScanButton: ImageButton

//    private lateinit var LogInButton : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_layout)

        FrontPageButton = findViewById(R.id.front_page)
        ScanButton = findViewById(R.id.scan)

        FrontPageButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ScanButton.setOnClickListener{
            val intent = Intent(this, ScanPage::class.java)
            startActivity(intent)
        }

//        LogInButton = findViewById(R.id.login)
//        LogInButton.setOnClickListener{
//            val intent = Intent(this, LoginPage::class.java)
//            startActivity(intent)
//        }
    }
}