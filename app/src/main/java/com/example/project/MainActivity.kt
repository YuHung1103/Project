package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {


    private lateinit var ScanButton: ImageButton
    private lateinit var MessageButton: ImageButton
    private lateinit var UserButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ScanButton = findViewById(R.id.scan)
        MessageButton = findViewById(R.id.message)
        UserButton = findViewById(R.id.user)


        ScanButton.setOnClickListener{
            val intent = Intent(this, ScanPage::class.java)
            startActivity(intent)
        }
        MessageButton.setOnClickListener{
            val intent = Intent(this, ChooseDatePage::class.java)
            startActivity(intent)
        }
        UserButton.setOnClickListener{
            val intent = Intent(this, UserPage::class.java)
            startActivity(intent)
        }
    }
}

