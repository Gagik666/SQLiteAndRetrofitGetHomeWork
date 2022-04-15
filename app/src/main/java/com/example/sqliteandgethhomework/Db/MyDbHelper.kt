package com.example.sqliteandgethhomework.Db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context): SQLiteOpenHelper(context, MyDb.DATABASE_NAME, null, MyDb.DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(MyDb.CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(MyDb.SQL_DELETE_TABLE)
        onCreate(p0)
    }
}