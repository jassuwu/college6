package io.jass.foodorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val continueBtn = findViewById<Button>(R.id.continueBtn)
        continueBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, UserDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}