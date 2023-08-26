package com.example.managebookapp.data

import kotlinx.coroutines.flow.Flow


interface SignedRepository {
    fun getAllSignedStream(): Flow<List<Signed>>

    suspend fun insertSigned(signed: Signed)

    suspend fun deleteAll()
}