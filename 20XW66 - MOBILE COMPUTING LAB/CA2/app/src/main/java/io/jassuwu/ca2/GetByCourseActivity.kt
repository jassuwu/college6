package io.jassuwu.ca2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GetByCourseActivity : AppCompatActivity() {
    private var studentModalArrayList: ArrayList<Alumni>? = null
    private var dbHandler: DBHandler? = null
    private var studentRVAdapter: AlumniRVAdapter? = null
    private var studentsRV: RecyclerView? = null
    private var courseEdt: EditText? = null
    private lateinit var getAlumByCourseBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_by_course)

        courseEdt = findViewById(R.id.idCourseToGetEdt)
        getAlumByCourseBtn = findViewById(R.id.idGetAlumByCourseBtn)

        getAlumByCourseBtn.setOnClickListener {
            val course = courseEdt!!.text.toString()

            studentModalArrayList = ArrayList();
            dbHandler = DBHandler(this)


            studentModalArrayList = dbHandler!!.getAlumniByCourse(course);

            studentRVAdapter = AlumniRVAdapter(studentModalArrayList!!, this);
            studentsRV = findViewById(R.id.idRVGetByCourse)

            val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            studentsRV!!.layoutManager = linearLayoutManager;

            studentsRV!!.adapter = studentRVAdapter;
        }


    }
}

