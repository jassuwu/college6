package io.jass.foodorderapp

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class UserDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val mobNumField = findViewById<EditText>(R.id.mobNum);
        val nameField = findViewById<EditText>(R.id.name);
        val captchaField = findViewById<TextView>(R.id.captchaField);
        val userInpField = findViewById<EditText>(R.id.userInpField);
        val submitBtn = findViewById<Button>(R.id.submitButton);

        // set the captcha
        captchaField.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            text = makeCaptcha();
        }
        // submission click
        submitBtn.setOnClickListener {
            if (mobNumField.text.toString().matches(Regex("^([+]\\d{2})?\\d{10}\$"))) {
                val intentToShop: Intent = Intent(applicationContext, ShopActivity::class.java);
                println(userInpField.text.toString() + "AND" + captchaField.text.toString());
                if(userInpField.text.toString() == captchaField.text.toString()) {
                    intentToShop.putExtra("mobNum", mobNumField.text.toString());
                    intentToShop.putExtra("name", nameField.text.toString());
                    startActivity(intentToShop);
                } else {
                    Toast.makeText(applicationContext, "your captcha sucks.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(applicationContext, "your mobile number sucks.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private fun makeCaptcha(): String {
        var captcha = "";
        for (i in 1..6  ){
            captcha += (0..9).random().toString();
        }
        return captcha;
    }

}