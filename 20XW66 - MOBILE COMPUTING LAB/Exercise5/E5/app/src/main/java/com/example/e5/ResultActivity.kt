package com.example.e5

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
        var str = "{\n\t\t\tmobile:";
        str += intent.getStringExtra("mobNum");
        str += ",\n\t\t\tstatus:";
        str += intent.getStringExtra("message");
        str += ",\n}";

        displayField.text = str;

        backBtn.setOnClickListener {
            finish();
        }
    }
}