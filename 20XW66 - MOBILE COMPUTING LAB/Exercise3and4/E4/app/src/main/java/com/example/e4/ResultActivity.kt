package com.example.e4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val displayField = findViewById<TextView>(R.id.displayField);
        val backBtn = findViewById<Button>(R.id.backButton);

        val intent = intent;
        var str = "{\n\t\t\tfName:";
        str += intent.getStringExtra("fName");
        str += ",\n\t\t\tlName:";
        str += intent.getStringExtra("lName");
        str += ",\n\t\t\tVisitType:";
        str += intent.getStringExtra("radioText");
        str += ",\n\t\t\tTo:";
        str += intent.getStringExtra("spinnerValue");
        str += ",\n}";

        displayField.text = str;

        backBtn.setOnClickListener {
            finish();
        }
    }
}