package com.example.managebookapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface BookFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: BookFavorite)

    @Update
    suspend fun update(item: BookFavorite)

    @Delete
    suspend fun delete(item: BookFavorite)

    @Query("SELECT * from book_favorites WHERE id = :id")
    fun getBookFavorite(id: Int): Flow<BookFavorite>

    @Query("SELECT * from book_favorites ORDER BY title ASC")
    fun getAllBookFavorites(): Flow<List<BookFavorite>>

    @Query("DELETE FROM book_favorites")
    fun deleteAll()
}