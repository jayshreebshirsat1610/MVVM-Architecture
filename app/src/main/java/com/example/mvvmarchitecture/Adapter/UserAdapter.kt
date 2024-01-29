package com.example.mvvmarchitecture.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmarchitecture.Model.User
import com.example.mvvmarchitecture.R

class UserAdapter(private val context: Context, private var userList:ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewModelHolder>() {
    inner class UserViewModelHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name :TextView = itemView.findViewById(R.id.name)
        val age : TextView = itemView.findViewById(R.id.age)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewModelHolder {
        return UserViewModelHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_row_item,parent,false))
    }

    override fun onBindViewHolder(holder: UserViewModelHolder, position: Int) {
        val user : User = userList[position]
        holder.name.text = user.name
        holder.age.text = user.age.toString()

    }
    fun setData(userList: ArrayList<User>){
        this.userList =  userList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}