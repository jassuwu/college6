package io.jassuwu.ca2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var goBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goBtn = findViewById(R.id.goBtn)

        goBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java));
        }
    }
}