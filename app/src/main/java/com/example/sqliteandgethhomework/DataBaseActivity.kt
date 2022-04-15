package com.example.sqliteandgethhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqliteandgethhomework.Adapter.UserDataBaseAdapter
import com.example.sqliteandgethhomework.Db.MyDb.dataList
import com.example.sqliteandgethhomework.Db.MyDbManager
import com.example.sqliteandgethhomework.databinding.ActivityDataBaseBinding


class DataBaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityDataBaseBinding
    lateinit var dataAdapter: UserDataBaseAdapter
    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDataBase.layoutManager = LinearLayoutManager(this)
        dataAdapter = UserDataBaseAdapter(this, dataList)
        binding.rvDataBase.adapter = dataAdapter

    }


}