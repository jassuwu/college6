package com.example.e4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // All the components
        val fName = findViewById<EditText>(R.id.fName);
        val lName = findViewById<EditText>(R.id.lName);
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup2);
        val spinner = findViewById<Spinner>(R.id.spinner)
        val btn = findViewById<Button>(R.id.button);

        // To populate the spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


        // To get the radio selected
        var radioText = "";
        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { _, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                radioText = radio.text.toString();
        });


        // verify the name fields and use the intent to send data to the ResultActivty
        btn.setOnClickListener {
            if (fName.text.toString().matches(Regex("[a-zA-Z]*")) && lName.text.toString().matches(Regex("[a-zA-Z]*"))) {
                val intent: Intent  = Intent(applicationContext, ResultActivity::class.java);
                intent.putExtra("fName", fName.text.toString());
                intent.putExtra("lName", lName.text.toString());
                intent.putExtra("radioText", radioText);
                intent.putExtra("spinnerValue", spinner.selectedItem.toString());
                startActivity(intent);
            } else {
                Toast.makeText(applicationContext, "Name fields should contain only alphabets.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    fun nextPage(view: View) {
    }
}