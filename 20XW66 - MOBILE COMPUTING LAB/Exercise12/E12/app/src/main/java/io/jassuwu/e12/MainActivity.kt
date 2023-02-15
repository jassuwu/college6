package io.jassuwu.e12

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // import all the fields and the submit button
        val dateField = findViewById<EditText>(R.id.date);
        val fromTimeField = findViewById<EditText>(R.id.fromTime);
        val toTimeField = findViewById<EditText>(R.id.toTime);
        val noOfKidsField = findViewById<EditText>(R.id.noOfKids);
        val addressField = findViewById<EditText>(R.id.address);
        val mobNumField = findViewById<EditText>(R.id.mobNum);
        val submitBtn = findViewById<Button>(R.id.submitBtn);

        // hashmap to store the date
        var date = HashMap<String, Int>()

        // DatePicker on clicking date field
        dateField.setOnClickListener(View.OnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val selectDate: Calendar = Calendar.getInstance()
                date["year"] = year
                date["month"] = month
                date["day"] = day
                selectDate.set(Calendar.YEAR, year)
                selectDate.set(Calendar.MONTH, month)
                selectDate.set(Calendar.DAY_OF_MONTH, day)
                println("DATE : $year $month $day")
                dateField.setText("$year/$month/$day");
            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        })

        // From and to time picker
        fromTimeField.setOnClickListener {
            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting our hour, minute.
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)


            val timePickerDialog = TimePickerDialog(
                this,
                { view, hourOfDay, minute ->
                    // add zeros to single digit numbers in hour and minutes
                    var hour = hourOfDay.toString()
                    var min = minute.toString()
                    if (hourOfDay < 10) {
                        hour = "0$hourOfDay"
                    }
                    if (minute < 10) {
                        min = "0$minute"
                    }
                    fromTimeField.setText("$hour :$min")
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }

        toTimeField.setOnClickListener {
            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting our hour, minute.
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)


            val timePickerDialog = TimePickerDialog(
                this,
                { view, hourOfDay, minute ->
                    // add zeros to single digit numbers in hour and minutes
                    var hour = hourOfDay.toString()
                    var min = minute.toString()
                    if (hourOfDay < 10) {
                        hour = "0$hourOfDay"
                    }
                    if (minute < 10) {
                        min = "0$minute"
                    }
                    toTimeField.setText("$hour :$min")
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }

        // submit button
        submitBtn.setOnClickListener {
            // get all the values from the fields
            val date = dateField.text.toString()
            val fromTime = fromTimeField.text.toString()
            val toTime = toTimeField.text.toString()
            val noOfKids = noOfKidsField.text.toString()
            val address = addressField.text.toString()
            val mobNum = mobNumField.text.toString()

            // Stringified json of all the values
            val json = """
                {
                    "date": "$date",
                    "fromTime": "$fromTime",
                    "toTime": "$toTime",
                    "noOfKids": "$noOfKids",
                    "address": "$address",
                    "mobNum": "$mobNum"
                }
            """.trimIndent()

            // check if any field is empty
            if (date == "" || fromTime == "" || toTime == "" || noOfKids == "" || address == "" || mobNum == "") {
                println("ERROR: Empty field")
            } else {
                Toast.makeText(this@MainActivity, "Your details saved", Toast.LENGTH_SHORT).show();
                // Create an intent for the notification
                // Create an intent for the notification
                val intent = Intent(applicationContext, DetailsActivity::class.java)
                intent.putExtra("json", json)
                val pendingIntent: PendingIntent =
                    PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                val mChannel = NotificationChannel("E12", "NotificationChannel", NotificationManager.IMPORTANCE_DEFAULT)
                val builder = Notification.Builder(
                    applicationContext,
                    "E12"
                )
                    .setSmallIcon(R.drawable.ic_android_black_24dp)
                    .setContentTitle("E12")
                    .setContentText(json)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

                val notificationManager: NotificationManager =
                    applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)
                notificationManager.notify(401, builder.build())

            }
        }
    }
}