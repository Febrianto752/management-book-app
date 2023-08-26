package com.example.managebookapp.screens

import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managebookapp.data.Signed
import com.example.managebookapp.data.SignedRepository
import com.example.managebookapp.data.User
import com.example.managebookapp.data.UsersRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class LoginViewModel(
    private val usersRepository: UsersRepository,
    private val signedRepository: SignedRepository
) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_0000L
    }

    suspend fun createUser(user: User) {
        usersRepository.insertUser(user)
    }

    fun login(email: String, password: String): Flow<User?> {
        return usersRepository.getUserByEmailAndPassword(email, password)
    }

    suspend fun insertSignedUser(user: User) {
        var signed = Signed(name = user.name, email = user.email)
        signedRepository.insertSigned(signed)
    }

    fun getAllSignedUser(): Flow<List<Signed>> {

        return signedRepository.getAllSignedStream()
    }

    suspend fun logout(): Flow<Boolean> {
        signedRepository.deleteAll()

        return flowOf(true);
    }

}