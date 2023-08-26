package com.example.managebookapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface SignedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(signed: Signed)

    @Query("SELECT * from signed")
    fun getSigneds(): Flow<List<Signed>>

    @Query("DELETE FROM signed")
    fun deleteAll()
}