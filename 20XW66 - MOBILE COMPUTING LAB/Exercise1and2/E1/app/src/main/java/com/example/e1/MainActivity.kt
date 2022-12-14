package com.example.e1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val CToFField = findViewById<EditText>(R.id.editTextTextPersonName);
        val gimmeFBtn = findViewById<Button>(R.id.button);
        val opView = findViewById<TextView>(R.id.textView4);

        gimmeFBtn.setOnClickListener {
            var celcius = CToFField.text.toString().toDouble();
            var result = (9.0/5)*celcius + 32;
            opView.setText(result.toString());
        }

    }
}