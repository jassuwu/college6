package com.example.e9

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Video
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.VideoView
import org.w3c.dom.Text
import kotlin.random.Random
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private var hidden: Boolean = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoView = findViewById<VideoView>(R.id.videoView);
        val videoPath = "android.resource://" + packageName + "/" + R.raw.video;
        val uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);


        val mediaController = MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val textView = findViewById<TextView>(R.id.textView)
        val rnd = Random(seed = 0);
        when (item.itemId) {
            R.id.play -> {
                if (!hidden) {
                    findViewById<VideoView>(R.id.videoView).start();
                    item.title = "Playing..."
                }

            };
            R.id.textColor -> {
                textView.setTextColor(Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                ));
                true;
            }

            R.id.small -> textView.textSize = resources.getDimension(R.dimen.S);
            R.id.medium -> textView.textSize = resources.getDimension(R.dimen.M);
            R.id.large -> textView.textSize = resources.getDimension(R.dimen.L);
            R.id.xlarge -> textView.textSize = resources.getDimension(R.dimen.XL)

            R.id.hide -> {
                hidden = !hidden;
                if (hidden) {
                    item.title = "Unhide VideoView";
                    findViewById<VideoView>(R.id.videoView).visibility = View.INVISIBLE;
                } else {
                    item.title = "Hide VideoView";
                    findViewById<VideoView>(R.id.videoView).visibility = View.VISIBLE;
                }
                true;
            }

            R.id.exit -> {
                finish();
                exitProcess(0);
                true;
            }
        }
        return super.onOptionsItemSelected(item)
    }
}