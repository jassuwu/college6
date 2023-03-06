package io.jassuwu.ca2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

class ActualActivity : AppCompatActivity() {

    private lateinit var rollNoEdt: EditText
    private lateinit var nameEdt: EditText
    private lateinit var courseEdt: EditText
    private lateinit var branchEdt: EditText
    private lateinit var yearEdt: EditText
    private lateinit var statusRG: RadioGroup
    private lateinit var whatEdt: EditText
    private lateinit var whereEdt: EditText
    private lateinit var submitBtn: Button
    private lateinit var viewBtn: Button
    private lateinit var getByCourseBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actual)

        rollNoEdt = findViewById(R.id.idRollNoEdt)
        nameEdt = findViewById(R.id.idNameEdt)
        courseEdt = findViewById(R.id.idCourseEdt)
        branchEdt = findViewById(R.id.idBranchEdt)
        yearEdt = findViewById(R.id.idYearEdt)
        statusRG = findViewById(R.id.idStatusRG)
        whatEdt = findViewById(R.id.idWhatEdt)
        whereEdt = findViewById(R.id.idWhereEdt)
        submitBtn = findViewById(R.id.idSubmitBtn)
        viewBtn = findViewById(R.id.idViewBtn)
        getByCourseBtn = findViewById(R.id.idGetByCourseBtn)

        val dbHandler = DBHandler(this)

        viewBtn.setOnClickListener {
            startActivity(Intent(this, ViewActivity::class.java))
        }

        getByCourseBtn.setOnClickListener {
            startActivity(Intent(this, GetByCourseActivity::class.java))
        }


        submitBtn.setOnClickListener {
            if (rollNoEdt.text.isEmpty() || nameEdt.text.isEmpty() || courseEdt.text.isEmpty() || branchEdt.text.isEmpty() || yearEdt.text.isEmpty() || whatEdt.text.isEmpty() || whereEdt.text.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                val rollNo = rollNoEdt.text.toString().toInt()
                val name = nameEdt.text.toString()
                val course = courseEdt.text.toString()
                val branch = branchEdt.text.toString()
                val year = yearEdt.text.toString().toInt()
                val status = when (statusRG.checkedRadioButtonId) {
                    R.id.idWorkingRB -> 1
                    R.id.idStudyingRB -> 0
                    else -> -1
                }
                val what = whatEdt.text.toString()
                val where = whereEdt.text.toString()

                dbHandler!!.addNewAluminum(rollNo, name, course, branch, year, status, what, where)
                Toast.makeText(this, "Alumni added successfully", Toast.LENGTH_SHORT).show()
                rollNoEdt.setText("")
                nameEdt.setText("")
                courseEdt.setText("")
                branchEdt.setText("")
                yearEdt.setText("")
                whatEdt.setText("")
                whereEdt.setText("")
            }
        }

    }


}