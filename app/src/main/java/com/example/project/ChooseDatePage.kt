package com.example.project

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ChooseDatePage : AppCompatActivity() {
    val checkinCalendar: Calendar = Calendar.getInstance()
    val checkoutCalendar: Calendar = Calendar.getInstance()
    private lateinit var checkin : EditText
    private lateinit var checkout : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_date_layout)
        //選入住日期
        checkin = findViewById(R.id.checkinDate)
        val chickInDate =
            OnDateSetListener { view, year, month, day ->
                checkinCalendar.set(Calendar.YEAR, year)
                checkinCalendar.set(Calendar.MONTH, month)
                checkinCalendar.set(Calendar.DAY_OF_MONTH, day)
                checkinUpdateLabel()
            }
        checkin!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                DatePickerDialog(
                    this@ChooseDatePage,
                    chickInDate,
                    checkinCalendar.get(Calendar.YEAR),
                    checkinCalendar.get(Calendar.MONTH),
                    checkinCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
        //選退房日期
        checkout = findViewById(R.id.checkoutDate)
        val chickOutDate =
            OnDateSetListener { view, year, month, day ->
                checkoutCalendar.set(Calendar.YEAR, year)
                checkoutCalendar.set(Calendar.MONTH, month)
                checkoutCalendar.set(Calendar.DAY_OF_MONTH, day)
                checkoutUpdateLabel()
            }
        checkout!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                DatePickerDialog(
                    this@ChooseDatePage,
                    chickOutDate,
                    checkoutCalendar.get(Calendar.YEAR),
                    checkoutCalendar.get(Calendar.MONTH),
                    checkoutCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
    }

    private fun checkinUpdateLabel() {
        val myFormat = "yyyy/MM/dd"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        checkin!!.setText(dateFormat.format(checkinCalendar.getTime()))
    }
    private fun checkoutUpdateLabel() {
        val myFormat = "yyyy/MM/dd"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        checkout!!.setText(dateFormat.format(checkoutCalendar.getTime()))
    }
}