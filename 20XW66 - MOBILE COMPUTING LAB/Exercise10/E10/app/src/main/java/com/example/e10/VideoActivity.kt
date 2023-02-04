package com.example.e10

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        val videoRaw = intent.getIntExtra("videoRaw", 0);
        val videoView = findViewById<VideoView>(R.id.videoView);
        val videoPath = "android.resource://$packageName/${videoRaw}";
        val uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        val mediaController = MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        val goBackBtn = findViewById<Button>(R.id.goback);
        goBackBtn.setOnClickListener {
            finish();
        }
    }
}