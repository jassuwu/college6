package io.jass.foodorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class OrderDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val deetsAreaField = findViewById<TextView>(R.id.deetsArea)

        val username = intent.getStringExtra("username")
        val mobNum = intent.getStringExtra("mobNum")
        val itemQuantity1 = intent.getStringExtra("itemQuantity1")
        val itemQuantity2 = intent.getStringExtra("itemQuantity2")
        val itemQuantity3 = intent.getStringExtra("itemQuantity3")
        val itemQuantity4 = intent.getStringExtra("itemQuantity4")
        val totalPrice = intent.getStringExtra("totalPrice")
        val confirmToFinalBtn = findViewById<Button>(R.id.confirmToFinalBtn)

        deetsAreaField.text = "Name: $username \n\n Mobile Number: $mobNum \n\n" +
                "Item 1: $itemQuantity1 \t\t Item 2: $itemQuantity2 \n\n" +
                "Item 3: $itemQuantity3 \t\t Item 4: $itemQuantity4 \n\n" +
                "Total: $totalPrice";

        confirmToFinalBtn.setOnClickListener {
            val intentToFinal = Intent(this@OrderDetailsActivity, FinalActivity::class.java)
            startActivity(intentToFinal)
        }
    }
}