package com.example.e11

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var dbHandler: DBHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fields
        val rollNoField = findViewById<EditText>(R.id.rollno);
        val nameField = findViewById<EditText>(R.id.name);
        val marksField = findViewById<EditText>(R.id.marks);

        // Buttons
        val insertBtn = findViewById<Button>(R.id.insertBtn);
        val deleteBtn = findViewById<Button>(R.id.deleteBtn);
        val updateBtn = findViewById<Button>(R.id.updateBtn);
        val viewBtn = findViewById<Button>(R.id.viewBtn);
        val viewAllBtn = findViewById<Button>(R.id.viewAllBtn);

        // Initializing the dbHandler
        dbHandler = DBHandler(applicationContext);


        // OnClick Listeners
        insertBtn.setOnClickListener {
            if (rollNoField.text.isEmpty() || nameField.text.isEmpty() || marksField.text.isEmpty()) {
                Toast.makeText(applicationContext, "Please enter all the data..", Toast.LENGTH_SHORT).show();
            } else {
                dbHandler!!.addNewStudent(rollNoField.text.toString().toIntOrNull(), nameField.text.toString(), marksField.text.toString().toDoubleOrNull() )
                Toast.makeText(applicationContext, "Student added.", Toast.LENGTH_SHORT).show();
                rollNoField.setText("");
                nameField.setText("");
                marksField.setText("");
            }
        }

        viewAllBtn.setOnClickListener {
            val intent = Intent(applicationContext, ViewAllActivity::class.java);
            startActivity(intent);
        }


    }
}