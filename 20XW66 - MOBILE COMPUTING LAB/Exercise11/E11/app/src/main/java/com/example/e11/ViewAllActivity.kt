package com.example.e11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ViewAllActivity : AppCompatActivity() {

    private var studentModalArrayList: ArrayList<StudentModal>? = null
    private var dbHandler: DBHandler? = null
    private var studentRVAdapter: StudentRVAdapter? = null
    private var studentsRV: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)

        studentModalArrayList = ArrayList();
        dbHandler = DBHandler(this@ViewAllActivity)

        studentModalArrayList = dbHandler!!.getStudents()

        studentRVAdapter = StudentRVAdapter(studentModalArrayList!!, this@ViewAllActivity);
        studentsRV = findViewById(R.id.idRVStudents)

        val linearLayoutManager = LinearLayoutManager(this@ViewAllActivity, RecyclerView.VERTICAL, false);
        studentsRV!!.layoutManager = linearLayoutManager;

        studentsRV!!.adapter = studentRVAdapter;
    }
}