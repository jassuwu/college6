package com.example.e1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cField = findViewById<EditText>(R.id.C);
        val fField = findViewById<EditText>(R.id.F);
        val convertBtn = findViewById<Button>(R.id.convertButton);

        convertBtn.setOnClickListener {
            if(cField.isFocused) {
                fField.setText(((cField.text.toString().toDouble()*9.0/5) + 32).toString());
            }
            if(fField.isFocused) {
                cField.setText(((fField.text.toString().toDouble()-32)*5.0/9).toString());
            }
        }

    }
}