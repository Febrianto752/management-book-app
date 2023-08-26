package com.example.managebookapp.data

import kotlinx.coroutines.flow.Flow


interface UsersRepository {
    fun getAllUsersStream(): Flow<List<User>>

    fun getUserStream(id: Int): Flow<User?>
    fun getUserByEmailAndPassword(email: String, password: String): Flow<User?>

    suspend fun insertUser(item: User)

    suspend fun deleteUser(item: User)
    suspend fun updateUser(item: User)

    suspend fun deleteAll()
}