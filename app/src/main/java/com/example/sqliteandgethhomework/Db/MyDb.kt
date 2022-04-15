package com.example.sqliteandgethhomework.Db

import android.provider.BaseColumns

object MyDb {
    const val TABLE_NAME = "uswer_table"
    const val COLUMN_NAME_NAME = "name"
    const val COLUMN_NAME_REALNAME = "real_name"
    const val COLUMN_NAME_TEAM = "team"
    const val COLUMN_NAME_IMAGEURL = "image_url"


    const val DATABASE_VERSION = 8
    const val DATABASE_NAME = "user.db"

    const val  CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_NAME TEXT, $COLUMN_NAME_REALNAME TEXT, $COLUMN_NAME_TEAM TEXT, $COLUMN_NAME_IMAGEURL TEXT)"


    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    var dataList = mutableListOf<UserDataModel>()
}