package com.example.mvvmarchitecture.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmarchitecture.Model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user:User)

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getAllUserData():LiveData<List<User>>

}