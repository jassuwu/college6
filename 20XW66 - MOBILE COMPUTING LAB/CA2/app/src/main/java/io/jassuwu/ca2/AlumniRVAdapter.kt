package io.jassuwu.ca2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class AlumniRVAdapter(studentModalArrayList: ArrayList<Alumni>, context: Context) :
    RecyclerView.Adapter<AlumniRVAdapter.ViewHolder>() {

    private var studentModalArrayList: ArrayList<Alumni>? = null
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
            LayoutInflater.from(parent.context).inflate(R.layout.alumni_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data
        // to our views of recycler view item.
        val modal = studentModalArrayList?.get(position) ?: null
        if (modal != null) {
            holder.alumRollNoTV.text = modal.rollNo.toString()
            holder.alumNameTV.text = modal.name
            holder.alumCourseTV.text = modal.course
            holder.alumBranchTV.text = modal.branch
            holder.alumYearTV.text = modal.year.toString()
            holder.alumStatusTV.text = modal.status.toString()
            holder.alumWhatTV.text = modal.what
            holder.alumWhereTV.text = modal.where

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
        val alumRollNoTV: TextView
        val alumNameTV: TextView
        val alumCourseTV: TextView
        val alumBranchTV: TextView
        val alumYearTV: TextView
        val alumStatusTV: TextView
        val alumWhatTV: TextView
        val alumWhereTV: TextView

        init {
            // initializing our text views
            alumRollNoTV = itemView.findViewById(R.id.idTVAlumRollNo)
            alumNameTV = itemView.findViewById(R.id.idTVAlumName)
            alumCourseTV = itemView.findViewById(R.id.idTVAlumCourse)
            alumBranchTV = itemView.findViewById(R.id.idTVAlumBranch)
            alumYearTV = itemView.findViewById(R.id.idTVAlumYear)
            alumStatusTV = itemView.findViewById(R.id.idTVAlumStatus)
            alumWhatTV = itemView.findViewById(R.id.idTVAlumWhat)
            alumWhereTV = itemView.findViewById(R.id.idTVAlumWhere)
        }
    }
}