package io.jass.foodorderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        val itemQuantity1Field = findViewById<EditText>(R.id.itemQuantity1)
        val itemQuantity2Field = findViewById<EditText>(R.id.itemQuantity2)
        val itemQuantity3Field = findViewById<EditText>(R.id.itemQuantity3)
        val itemQuantity4Field = findViewById<EditText>(R.id.itemQuantity4)
        val submitToShopBtn = findViewById<Button>(R.id.submitToShopBtn)

        submitToShopBtn.setOnClickListener {
            val itemQuantity1 = itemQuantity1Field.text.toString().toInt()
            val itemQuantity2 = itemQuantity2Field.text.toString().toInt()
            val itemQuantity3 = itemQuantity3Field.text.toString().toInt()
            val itemQuantity4 = itemQuantity4Field.text.toString().toInt()
            val totalPrice = itemQuantity1*1000 + itemQuantity2*500 + itemQuantity3*750 + itemQuantity4*200

            val intentToOrderDetails = Intent(this@ShopActivity, OrderDetailsActivity::class.java)
            intentToOrderDetails.putExtra("username", intent.getStringExtra("name"))
            intentToOrderDetails.putExtra("mobNum", intent.getStringExtra("mobNum"))
            intentToOrderDetails.putExtra("itemQuantity1", itemQuantity1.toString())
            intentToOrderDetails.putExtra("itemQuantity2", itemQuantity2.toString())
            intentToOrderDetails.putExtra("itemQuantity3", itemQuantity3.toString())
            intentToOrderDetails.putExtra("itemQuantity4", itemQuantity4.toString())
            intentToOrderDetails.putExtra("totalPrice", totalPrice.toString())
            startActivity(intentToOrderDetails)
        }
    }
}