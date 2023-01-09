package com.example.e7

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu.OnMenuItemClickListener
import android.widget.RelativeLayout
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val rl = findViewById<RelativeLayout>(R.id.rl);
        when (item.itemId) {
            R.id.red -> rl.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.red));
            R.id.green -> rl.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.green));
            R.id.blue -> rl.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.blue));
            R.id.yellow -> rl.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.yellow));
        }
        return super.onOptionsItemSelected(item)
    }

    fun showPopup(v:View) {
        val popup = PopupMenu(this, v);
        val rnd = Random()
        val btn = findViewById<Button>(R.id.popupbutton)
        popup.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.small -> btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
                R.id.med -> btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
                R.id.large -> btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                R.id.randColor -> btn.setTextColor(
                    Color.argb(
                        255,
                        rnd.nextInt(256),
                        rnd.nextInt(256),
                        rnd.nextInt(256)
                    )
                )
            }
            false
        };
        popup.inflate(R.menu.popup);
        popup.show();
    }

}