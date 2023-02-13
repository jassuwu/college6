package com.example.e11

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class StudentRVAdapter(studentModalArrayList: ArrayList<StudentModal>, context: Context) :
    RecyclerView.Adapter<StudentRVAdapter.ViewHolder>() {

    private var studentModalArrayList: ArrayList<StudentModal>? = null
    private var context: Context? = null

    // constructor
    init {
        this.studentModalArrayList = studentModalArrayList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // on below line we are inflating our layout
        // file for our recycler view items.
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data
        // to our views of recycler view item.
        val modal = studentModalArrayList?.get(position) ?: null
        if (modal != null) {
            holder.studentRollNoTV.text = modal.rollNo.toString()
            holder.studentNameTV.text = modal.name
            holder.studentMarksTV.text = modal.marks.toString()
        }
        else {
            throw Error("There was an error in binding View holder.")
        }
    }

    override fun getItemCount(): Int {
        // returning the size of our array list
        return studentModalArrayList?.size ?: -1;
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our text views.
        val studentRollNoTV: TextView
        val studentNameTV: TextView
        val studentMarksTV: TextView

        init {
            // initializing our text views
            studentRollNoTV = itemView.findViewById(R.id.idTVStudentRollNo)
            studentNameTV = itemView.findViewById(R.id.idTVStudentName)
            studentMarksTV = itemView.findViewById(R.id.idTVStudentMarks)
        }
    }
}
