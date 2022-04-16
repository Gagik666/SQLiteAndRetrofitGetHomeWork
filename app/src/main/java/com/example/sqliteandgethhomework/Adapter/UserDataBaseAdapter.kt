package com.example.sqliteandgethhomework.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteandgethhomework.Db.UserDataModel
import com.example.sqliteandgethhomework.R
import com.example.sqliteandgethhomework.databinding.ItemDataBaseBinding

import com.squareup.picasso.Picasso

class UserDataBaseAdapter(
    private val context: Context,
    private val dataList: MutableList<UserDataModel>
): RecyclerView.Adapter<UserDataBaseAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemDataBaseBinding.bind(itemView)
        fun bind(userDataModel: UserDataModel) = with(binding) {
            tvDataName.text = userDataModel.dataName
            tvDataRealName.text = userDataModel.dataRealName
            tvDataTeam.text = userDataModel.dataTeam
            tvDataImageUrl.text = userDataModel.dataImageUrl
            Picasso.get().load(userDataModel.dataImageUrl).into(imgDatabase)

            tvDataImageUrl.setOnClickListener {
                tvDataImageUrl.visibility = View.INVISIBLE
                imgDatabase.visibility = View.VISIBLE
            }

            imgDatabase.setOnClickListener {
                tvDataImageUrl.visibility = View.VISIBLE
                imgDatabase.visibility = View.GONE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_data_base, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}