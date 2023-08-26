package com.example.managebookapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managebookapp.data.User
import com.example.managebookapp.data.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class UserViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    var usersList by mutableStateOf<List<User>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            var users = usersRepository.getAllUsersStream()
            users.collect { userListParam ->
                usersList = userListParam;
            }
        }


    }

    suspend fun updateUserList(){
        var users = usersRepository.getAllUsersStream()
        users.collect{userListParam ->
            usersList = userListParam;
        }
    }

    suspend fun createUser(user: User) {
        usersRepository.insertUser(user)
        updateUserList()
    }

    suspend fun deleteUser(user: User){
        usersRepository.deleteUser(user)
        updateUserList()
    }

    suspend fun deleteAllUser(){
        usersRepository.deleteAll()
        updateUserList()
    }
}