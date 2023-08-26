package com.example.managebookapp.data

import kotlinx.coroutines.flow.Flow

interface BookFavoritesRepository {
    fun getAllBookFavoritesStream(): Flow<List<BookFavorite>>

    fun getBookFavoriteStream(id: Int): Flow<BookFavorite?>

    suspend fun insertBookFavorite(item: BookFavorite)

    suspend fun deleteBookFavorite(item: BookFavorite)
    suspend fun updateBookFavorite(item: BookFavorite)

    suspend fun deleteAll()
}