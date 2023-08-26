package com.example.managebookapp.data

import kotlinx.coroutines.flow.Flow


class OfflineBookFavoritesRepository(private val bookFavoriteDao: BookFavoriteDao) : BookFavoritesRepository {
    override fun getAllBookFavoritesStream(): Flow<List<BookFavorite>> = bookFavoriteDao.getAllBookFavorites()

    override fun getBookFavoriteStream(id: Int): Flow<BookFavorite?> = bookFavoriteDao.getBookFavorite(id)

    override suspend fun insertBookFavorite(item: BookFavorite) = bookFavoriteDao.insert(item)

    override suspend fun deleteBookFavorite(item: BookFavorite) = bookFavoriteDao.delete(item)

    override suspend fun updateBookFavorite(item: BookFavorite) = bookFavoriteDao.update(item)

    override suspend fun deleteAll() = bookFavoriteDao.deleteAll()

}