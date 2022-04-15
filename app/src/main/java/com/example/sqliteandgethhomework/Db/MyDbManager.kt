package com.example.sqliteandgethhomework.Db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = myDbHelper.writableDatabase
    }

    fun insertToDB(name: String, realName: String, team: String, imgUrl: String) {
        val values = ContentValues().apply {
            put(MyDb.COLUMN_NAME_NAME, name)
            put(MyDb.COLUMN_NAME_REALNAME, realName)
            put(MyDb.COLUMN_NAME_TEAM, team)
            put(MyDb.COLUMN_NAME_IMAGEURL, imgUrl)
        }
        db?.insert(MyDb.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDb(): Set<UserDataModel> {
        val datalist = ArrayList<UserDataModel>()
        val cursor = db?.query(MyDb.TABLE_NAME, null, null,
            null, null, null, null,)
        while (cursor?.moveToNext()!!) {

            val dataName = cursor.getString(cursor.getColumnIndex(MyDb.COLUMN_NAME_NAME))
            val dataRealName = cursor.getString(cursor.getColumnIndex(MyDb.COLUMN_NAME_REALNAME))
            val dataTeam = cursor.getString(cursor.getColumnIndex(MyDb.COLUMN_NAME_TEAM))
            val dataImageUrl = cursor.getString(cursor.getColumnIndex(MyDb.COLUMN_NAME_IMAGEURL))
            datalist.add(UserDataModel(dataName, dataRealName, dataTeam, dataImageUrl))
        }
        cursor.close()
        return datalist.toSet()
    }

    fun onCloseDb() {
        myDbHelper.close()
    }
}