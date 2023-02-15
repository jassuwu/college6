package io.jassuwu.e12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val detailsTextField = findViewById<TextView>(R.id.detailsText);
        val okayBtn = findViewById<Button>(R.id.okay);

        val json = intent.getStringExtra("json");
        detailsTextField.text = json;

        okayBtn.setOnClickListener {
            finish();
        }
    }
}