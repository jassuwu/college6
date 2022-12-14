package com.example.e2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imgView = findViewById<ImageView>(R.id.imageView2);
        val feedBtn = findViewById<Button>(R.id.button);

        feedBtn.setOnClickListener {
            if(feedBtn.text == "FEED THE PEPE") {
                feedBtn.text = "TAKE THE FOOD";
                imgView.setImageResource(R.drawable.pepecocacola);
            } else {
                feedBtn.text = "FEED THE PEPE";
                imgView.setImageResource(R.drawable.pepehungry);
            }
        }
    }
}