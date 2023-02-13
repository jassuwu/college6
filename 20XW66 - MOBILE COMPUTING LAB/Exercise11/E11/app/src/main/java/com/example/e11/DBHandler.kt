package com.example.e11

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHandler  // creating a constructor for our database handler.
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // below method is for creating a database by running a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        val query = ("CREATE TABLE $TABLE_NAME (" +
                "$ROLLNO_COL INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NAME_COL TEXT," +
                "$MARKS_COL TEXT)")
        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query)
    }

    // this method is use to add new course to our sqlite database.
    fun addNewStudent(
        rollNo: Int?,
        name: String?,
        marks: Double?,
    ) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase

        // on below line we are creating a
        // variable for content values.
        val values = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ROLLNO_COL, rollNo)
        values.put(NAME_COL, name)
        values.put(MARKS_COL, marks)

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values)

        // at last we are closing our
        // database after adding database.
        db.close()
    }

    fun getStudents(): ArrayList<StudentModal> {
        val db = this.writableDatabase

        val cursorStudents = db.rawQuery("SELECT * FROM $TABLE_NAME;", null);

        val studentsList = ArrayList<StudentModal>();

        if (cursorStudents.moveToFirst()) {
            do {
                studentsList += StudentModal(cursorStudents.getInt(0), cursorStudents.getString(1), cursorStudents.getDouble(2));
            } while (cursorStudents.moveToNext())
        }

        cursorStudents.close()
        return studentsList;
    }

    fun getStudent(rollNo: Int): StudentModal {
        val db = this.writableDatabase

        val cursorStudent = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $ROLLNO_COL = $rollNo;", null);

        cursorStudent.moveToFirst()

        val student = StudentModal(cursorStudent.getInt(0), cursorStudent.getString(1), cursorStudent.getDouble(2));

        cursorStudent.close()
        return student;
    }

    fun updateStudent(rollNo: Int, name: String, marks: Double) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(NAME_COL, name)
        values.put(MARKS_COL, marks)

        db.update(TABLE_NAME, values, "$ROLLNO_COL = ?", arrayOf(rollNo.toString()))
        db.close()
    }

    fun deleteStudent(rollNo: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$ROLLNO_COL = ?", arrayOf(rollNo.toString()))
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME;")
        onCreate(db)
    }

    companion object {
        // creating a constant variables for our database.
        // below variable is for our database name.
        private const val DB_NAME = "studentsdb"

        // below int is our database version
        private const val DB_VERSION = 1

        // below variable is for our table name.
        private const val TABLE_NAME = "students"

        // below variable is for our id column.
        private const val ROLLNO_COL = "rollno"

        // below variable is for our course name column
        private const val NAME_COL = "name"

        // below variable id for our course duration column.
        private const val MARKS_COL = "marks"
    }
}
