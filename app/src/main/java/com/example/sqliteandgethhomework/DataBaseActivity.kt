package com.example.sqliteandgethhomework

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqliteandgethhomework.Adapter.UserDataBaseAdapter
import com.example.sqliteandgethhomework.Db.MyDb.dataList
import com.example.sqliteandgethhomework.Db.MyDbManager
import com.example.sqliteandgethhomework.databinding.ActivityDataBaseBinding


class DataBaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityDataBaseBinding
    lateinit var dataAdapter: UserDataBaseAdapter
    var imageRecuestCode = 10
    var tempImgUri = "Empty"
    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDataBase.layoutManager = LinearLayoutManager(this)
        dataAdapter = UserDataBaseAdapter(this, dataList)
        binding.rvDataBase.adapter = dataAdapter

        binding.btnAddImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, imageRecuestCode)
        }

        binding.btnSave.setOnClickListener {

            myDbManager.openDb()
            dataList = myDbManager.readDb().toMutableList()
            myDbManager.insertToDB(
                binding.edName.text.toString(),
                binding.edRealName.text.toString(),
                binding.edTeam.text.toString(),
                tempImgUri
            )

            binding.btnSave.visibility = View.GONE
            binding.lyAdd.visibility = View.GONE
            binding.btnAddUsers.visibility = View.VISIBLE
            binding.btnCancel.visibility = View.VISIBLE
            addUser()
            finish()
        }

        binding.btnAddUsers.setOnClickListener {
            binding.lyAdd.visibility = View.VISIBLE
            binding.btnSave.visibility = View.VISIBLE
            binding.btnAddUsers.visibility = View.GONE
            binding.btnCancel.visibility = View.GONE
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && resultCode == imageRecuestCode) {
            tempImgUri = data?.data.toString()
        }
    }

    override fun onResume() {
        super.onResume()
        addUser()
    }

    fun addUser() {
        binding.rvDataBase.layoutManager = LinearLayoutManager(this)
        dataAdapter = UserDataBaseAdapter(this, dataList)
        binding.rvDataBase.adapter = dataAdapter
        dataAdapter.notifyDataSetChanged()
    }
}