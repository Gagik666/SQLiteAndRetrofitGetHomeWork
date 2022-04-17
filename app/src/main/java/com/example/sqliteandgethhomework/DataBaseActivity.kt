package com.example.sqliteandgethhomework

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        binding.imgAddImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, imageRecuestCode)
        }

        if (binding.edName.text.toString() == ""
            && binding.edRealName.text.toString() == ""
            && binding.edTeam.text.toString() == "")
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
        else
            binding.imgSave.isClickable

        binding.imgSave.setOnClickListener {

            myDbManager.openDb()
            dataList = myDbManager.readDb().toMutableList()
            myDbManager.insertToDB(
                binding.edName.text.toString(),
                binding.edRealName.text.toString(),
                binding.edTeam.text.toString(),
                tempImgUri
            )

            binding.imgSave.visibility = View.GONE
            binding.lyAdd.visibility = View.GONE
            binding.imgAddUsers.visibility = View.VISIBLE
            binding.imgCancel.visibility = View.VISIBLE
            addUser()
            finish()
        }


        binding.imgAddUsers.setOnClickListener {
            binding.lyAdd.visibility = View.VISIBLE
            binding.imgSave.visibility = View.VISIBLE
            binding.imgAddUsers.visibility = View.GONE
            binding.imgCancel.visibility = View.GONE
        }

        binding.imgCancel.setOnClickListener {
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