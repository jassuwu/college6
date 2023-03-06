package io.jassuwu.ca2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    private lateinit var passwordEdt: EditText
    private lateinit var loginBtn: Button

    private val PASSWORD = "root"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        passwordEdt = findViewById(R.id.passwordEdt)
        loginBtn = findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val password = passwordEdt.text.toString()
            if (password == PASSWORD) {
                startActivity(Intent(this, ActualActivity::class.java))
            }
        }
    }
}