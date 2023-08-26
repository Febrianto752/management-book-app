package com.example.managebookapp.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managebookapp.data.User
import com.example.managebookapp.data.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class RegistrationViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    fun createUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.insertUser(user)
        }
    }

    fun getAllItems(): Flow<List<User>> {
//        GlobalScope.launch{
//            usersRepository.deleteAll()
//        }

        return usersRepository.getAllUsersStream()
    }



}