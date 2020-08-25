package com.example.latticesignup

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun Insert(userJson : UserJson)

    @Query("SELECT * FROM USER_DATA")
    fun getUsersData() : LiveData<List<UserJson>>
}