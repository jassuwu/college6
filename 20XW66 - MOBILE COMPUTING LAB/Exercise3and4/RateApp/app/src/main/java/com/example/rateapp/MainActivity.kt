package com.example.rateapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar);
        val feeling = findViewById<TextView>(R.id.feeling);
        val feedback = findViewById<EditText>(R.id.feedback);
        val submitBtn = findViewById<Button>(R.id.submitBtn);

        ratingBar.onRatingBarChangeListener = OnRatingBarChangeListener {
            _, rating, _ ->
            if(rating.equals(1.0f)) {
                feeling.text = "Disappointed. Very poor.";
            } else if (rating.equals(2.0f)) {
                feeling.text = "Not good. Need improvement."
            } else if (rating.equals(3.0f)) {
                feeling.text = "Satisfied."
            } else if (rating.equals(4.0f)) {
                feeling.text = "Good. Enjoyed it."
            } else {
                feeling.text = "Awesome. I love it."
            }
        }

        submitBtn.setOnClickListener {
            Toast.makeText(
                this@MainActivity, feedback.text, Toast.LENGTH_SHORT
            ).show();
        }
    }
}