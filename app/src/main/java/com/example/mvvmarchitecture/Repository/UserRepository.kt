package com.example.mvvmarchitecture.Repository


import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mvvmarchitecture.Database.UserDatabase
import com.example.mvvmarchitecture.Model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository {
    companion object{
        private var userDatabase:UserDatabase?=null

        fun initilizeDB(context: Context):UserDatabase? {
            return  UserDatabase.getInstance(context)

        }
        fun insert(context: Context,user:User){
            userDatabase=initilizeDB(context)
            CoroutineScope(IO).launch {
                userDatabase?.getDao()?.insert(user)
            }

        }
        fun getAllUserData(context: Context): LiveData<List<User>>? {
            userDatabase= initilizeDB(context)
            return userDatabase?.getDao()?.getAllUserData()

        }

    }
}