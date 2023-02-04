package com.example.e10

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

internal class GridRVAdapter (
    private val videoList: List<GridViewModal>,
            private val context: Context
) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var videoTV: TextView
    private lateinit var videoIV: ImageView

    override fun getCount(): Int {
        return videoList.size;
    }

    override fun getItem(position: Int): Any? {
        return null;
    }

    override fun getItemId(position: Int): Long {
        return 0;
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView;

        if (layoutInflater == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
        }

        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.grid_item, null);
        }

        videoIV = convertView!!.findViewById(R.id.idIVVideo)
        videoTV = convertView!!.findViewById(R.id.idTVVideo)

        videoIV.setImageResource(videoList[position].videoThumbNail)
        videoTV.text = videoList[position].videoTitle;

        return convertView;
    }
}