package com.example.latticesignup

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class UserRepository(private val userDao: UserDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allUsers: LiveData<List<UserJson>> = userDao.getUsersData()

    suspend fun insert(userJson: UserJson) {
        userDao.Insert(userJson)
    }
}