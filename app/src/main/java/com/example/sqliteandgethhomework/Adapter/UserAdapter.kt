package com.example.sqliteandgethhomework.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteandgethhomework.Db.UserDataModel
import com.example.sqliteandgethhomework.Model.MyDataItem
import com.example.sqliteandgethhomework.R
import com.example.sqliteandgethhomework.databinding.ItemUsersBinding
import com.squareup.picasso.Picasso

class UserAdapter (
    private val context: Context,
    private val userList: MutableList<UserDataModel>
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemUsersBinding.bind(itemView)
        fun bind(myDataItem: UserDataModel) = with(binding) {
            tvName.text = myDataItem.dataName
            tvRealName.text = myDataItem.dataRealName
            tvTeam.text = myDataItem.dataTeam
            Picasso.get().load(myDataItem.dataImageUrl).into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size

}