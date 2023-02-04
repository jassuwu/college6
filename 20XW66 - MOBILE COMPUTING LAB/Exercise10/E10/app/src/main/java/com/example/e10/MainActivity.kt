package com.example.e10

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var videoGRV: GridView
    lateinit var videoList: List<GridViewModal>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoGRV = findViewById(R.id.idGRV)
        videoList = ArrayList()

        videoList = videoList + GridViewModal("nyancat (original)", R.drawable.nyancat, R.raw.nyancat);
        videoList = videoList + GridViewModal("nyaning cat", R.drawable.nyancat, R.raw.nyancat);
        videoList = videoList + GridViewModal("the cat that nyans", R.drawable.nyancat, R.raw.nyancat);
        videoList = videoList + GridViewModal("cat of nyan", R.drawable.nyancat, R.raw.nyancat);

        val videoAdapter = GridRVAdapter(videoList = videoList, this@MainActivity)

        videoGRV.adapter = videoAdapter;

        videoGRV.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(applicationContext, VideoActivity::class.java);
            intent.putExtra("videoTitle", videoList[i].videoTitle);
            intent.putExtra("videoThumbnail", videoList[i].videoThumbNail);
            intent.putExtra("videoRaw", videoList[i].videoRaw);
            startActivity(intent);
        }
    }
}