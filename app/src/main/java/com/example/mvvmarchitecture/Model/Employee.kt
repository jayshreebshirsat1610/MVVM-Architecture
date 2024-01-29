package com.example.mvvmarchitecture.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    val name:String,
    val emailId:String,
    val passward:String,
    val empCode:String,
) {
    @PrimaryKey
    var id:Int?=null
}