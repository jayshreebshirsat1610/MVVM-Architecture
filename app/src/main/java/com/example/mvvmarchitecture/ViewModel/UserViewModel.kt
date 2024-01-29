package com.example.mvvmarchitecture.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmarchitecture.Model.User
import com.example.mvvmarchitecture.Repository.UserRepository

class UserViewModel: ViewModel() {
    fun insert(context: Context,user: User){
        UserRepository.insert(context,user)
    }
    fun  getAllUserData(context: Context): LiveData<List<User>>? {
            return UserRepository.getAllUserData(context)
    }

}