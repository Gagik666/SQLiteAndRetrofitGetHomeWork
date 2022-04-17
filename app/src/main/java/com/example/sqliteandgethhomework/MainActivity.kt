package com.example.sqliteandgethhomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqliteandgethhomework.Adapter.UserAdapter
import com.example.sqliteandgethhomework.ApiInterface.NewService.retrofitBulder
import com.example.sqliteandgethhomework.Db.MyDb.dataList
import com.example.sqliteandgethhomework.Db.MyDbManager
import com.example.sqliteandgethhomework.Model.MyDataItem
import com.example.sqliteandgethhomework.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: UserAdapter
    val myDbManager= MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)

            getMyData()



        binding.btnDatabase.setOnClickListener {
            val i = Intent(this, DataBaseActivity::class.java)
            startActivity(i)
        }

//            for (i in dataList) {
//                binding.tv.append("${i.dataName}  ${i.dataRealName}  ${i.dataTeam}  ${i.dataImageUrl}")
//                binding.tv.append("\n")



    }



    private fun getMyData() {
        dataList.clear()
        val list = mutableListOf<MyDataItem>()

        val retrofitData = retrofitBulder.getData()
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!
                for (item in responseBody) {
                    list.add(item)
                }

                list.forEach {
                    myDbManager.insertToDB(it.name, it.realname, it.team, it.imageurl)
                }

            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MyLog", "ofFailure ${t.message}")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        myDbManager.openDb()
        dataList = myDbManager.readDb().toMutableList()
        adapter = UserAdapter(this@MainActivity, dataList)
        binding.rvUsers.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.onCloseDb()
    }
}