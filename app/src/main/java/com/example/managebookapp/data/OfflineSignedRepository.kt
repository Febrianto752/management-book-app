package com.example.managebookapp.data

import kotlinx.coroutines.flow.Flow

class OfflineSignedRepository(private val signedRepository: SignedDao) : SignedRepository {
    override fun getAllSignedStream(): Flow<List<Signed>> = signedRepository.getSigneds()

    override suspend fun insertSigned(signed: Signed) = signedRepository.insert(signed)

    override suspend fun deleteAll() = signedRepository.deleteAll()

}