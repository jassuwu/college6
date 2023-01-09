package com.example.e8

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    private var formatDate = SimpleDateFormat("dd MMM YYYY", Locale.US)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateFormatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy")
        var startDate: HashMap<String, Int> = HashMap()
        var endDate: HashMap<String, Int> = HashMap()
        var from: Date = Date();
        var to: Date = Date();

        val yField = findViewById<TextView>(R.id.yField)
        val mField = findViewById<TextView>(R.id.mField)
        val dField = findViewById<TextView>(R.id.dField)


        val diffButton = findViewById<Button>(R.id.button3);

        val startDateButton = findViewById<Button>(R.id.startDateButton);
        startDateButton.setOnClickListener(View.OnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val selectDate: Calendar = Calendar.getInstance()
                startDate["year"] = year
                startDate["month"] = month
                startDate["day"] = day
                selectDate.set(Calendar.YEAR, year)
                selectDate.set(Calendar.MONTH, month)
                selectDate.set(Calendar.DAY_OF_MONTH, day)
                println("DATE : $year $month $day")
                val date = formatDate.format(selectDate.time)
                from = selectDate.time
                startDateButton.text = date
            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        })

        val endDateButton = findViewById<Button>(R.id.endDateButton);
        endDateButton.setOnClickListener(View.OnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val selectDate: Calendar = Calendar.getInstance()
                endDate["year"] = year
                endDate["month"] = month
                endDate["day"] = day
                selectDate.set(Calendar.YEAR, year)
                selectDate.set(Calendar.MONTH, month)
                selectDate.set(Calendar.DAY_OF_MONTH, day)
                println("DATE : $year $month $day")
                val date = formatDate.format(selectDate.time)
                to = selectDate.time
                endDateButton.text = date
            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        })

        diffButton.setOnClickListener {
            val period = Period.between(from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            yField.text = period.years.toString()
            mField.text = period.months.toString()
            dField.text = period.days.toString()
        }
    }
}