package com.example.latticesignup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewmodel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    val allUsers: LiveData<List<UserJson>>

    init {
        val usersDao = UserRoomDatabase.getDatabase(application).userDao()
        repository = UserRepository(usersDao)
        allUsers = repository.allUsers
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(userJson: UserJson) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(userJson)
    }
}