package io.jassuwu.ca2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewActivity : AppCompatActivity() {

    private var studentModalArrayList: ArrayList<Alumni>? = null
    private var dbHandler: DBHandler? = null
    private var studentRVAdapter: AlumniRVAdapter? = null
    private var studentsRV: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        studentModalArrayList = ArrayList();
        dbHandler = DBHandler(this)

        studentModalArrayList = dbHandler!!.getAlumni()

        studentRVAdapter = AlumniRVAdapter(studentModalArrayList!!, this);
        studentsRV = findViewById(R.id.idRVStudents)

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        studentsRV!!.layoutManager = linearLayoutManager;

        studentsRV!!.adapter = studentRVAdapter;
    }
}