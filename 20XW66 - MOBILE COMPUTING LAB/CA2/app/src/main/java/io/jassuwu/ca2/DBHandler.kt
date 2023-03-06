package io.jassuwu.ca2

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
                "$COURSE_COL TEXT," +
                "$BRANCH_COL TEXT," +
                "$YEAR_COL INTEGER," +
                "$STATUS_COL INTEGER," +
                "$WHAT_COL TEXT," +
                "$WHERE_COL TEXT)")
        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query)
    }

    // this method is use to add new course to our sqlite database.
    fun addNewAluminum(
        rollNo: Int?,
        name: String?,
        course: String?,
        branch: String?,
        year: Int?,
        status: Int?,
        what: String?,
        where: String?
    ) {

        val db = this.writableDatabase
        val values = ContentValues()

        values.put(ROLLNO_COL, rollNo)
        values.put(NAME_COL, name)
        values.put(COURSE_COL, course)
        values.put(BRANCH_COL, branch)
        values.put(YEAR_COL, year)
        values.put(STATUS_COL, status)
        values.put(WHAT_COL, what)
        values.put(WHERE_COL, where)

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values)

        // at last we are closing our
        // database after adding database.
        db.close()
    }

    fun getAlumni(): ArrayList<Alumni> {
        val db = this.writableDatabase

        val cursorStudents = db.rawQuery("SELECT * FROM $TABLE_NAME;", null);
        val studentsList = ArrayList<Alumni>();
        if (cursorStudents.moveToFirst()) {
            do {
                studentsList += Alumni(cursorStudents.getInt(0), cursorStudents.getString(1), cursorStudents.getString(2), cursorStudents.getString(3), cursorStudents.getInt(4), cursorStudents.getInt(5), cursorStudents.getString(6), cursorStudents.getString(7));
            } while (cursorStudents.moveToNext())
        }
        cursorStudents.close()
        return studentsList;
    }

    fun getAluminum(rollNo: Int): Alumni {
        val db = this.writableDatabase
        val cursorStudent = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $ROLLNO_COL = $rollNo;", null);
        cursorStudent.moveToFirst()
        val alumni = Alumni(cursorStudent.getInt(0), cursorStudent.getString(1), cursorStudent.getString(2), cursorStudent.getString(3), cursorStudent.getInt(4), cursorStudent.getInt(5), cursorStudent.getString(6), cursorStudent.getString(7));
        cursorStudent.close()
        return alumni;
    }

    // Get Alumni by course
    fun getAlumniByCourse(course: String): ArrayList<Alumni> {
        val db = this.writableDatabase

        val cursorStudents = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COURSE_COL = '$course';", null);
        val studentsList = ArrayList<Alumni>();
        if (cursorStudents.moveToFirst()) {
            do {
                studentsList += Alumni(cursorStudents.getInt(0), cursorStudents.getString(1), cursorStudents.getString(2), cursorStudents.getString(3), cursorStudents.getInt(4), cursorStudents.getInt(5), cursorStudents.getString(6), cursorStudents.getString(7));
            } while (cursorStudents.moveToNext())
        }
        cursorStudents.close()
        return studentsList;
    }
//    fun updateStudent(rollNo: Int, name: String, marks: Double) {
//        val db = this.writableDatabase
//        val values = ContentValues()
//
//        values.put(NAME_COL, name)
//
//        values.put(MARKS_COL, marks)
//
//        db.update(TABLE_NAME, values, "$ROLLNO_COL = ?", arrayOf(rollNo.toString()))
//        db.close()
//    }

//    fun deleteStudent(rollNo: Int) {
//        val db = this.writableDatabase
//        db.delete(TABLE_NAME, "$ROLLNO_COL = ?", arrayOf(rollNo.toString()))
//        db.close()
//    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME;")
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "alumnidb"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "alumni"
        private const val ROLLNO_COL = "rollno"
        private const val NAME_COL = "name"
        private const val COURSE_COL = "course"
        private const val BRANCH_COL = "branch"
        private const val YEAR_COL = "year"
        private const val STATUS_COL = "status"
        private const val WHAT_COL = "what"
        private const val WHERE_COL = "wheree"
    }
}