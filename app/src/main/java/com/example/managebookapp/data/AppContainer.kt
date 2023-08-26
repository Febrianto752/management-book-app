package com.example.managebookapp.data

import android.content.Context

interface AppContainer {
    val usersRepository: UsersRepository
    val bookFavoritesRepository: BookFavoritesRepository
    val signedRepository: SignedRepository

}

/**
 * [AppContainer] implementation that provides instance of [OfflineUsersRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [UsersRepository]
     */
    override val usersRepository: UsersRepository by lazy {
        OfflineUsersRepository(ManagementBookDatabase.getDatabase(context).userDao())
    }

    override val bookFavoritesRepository: BookFavoritesRepository by lazy{
        OfflineBookFavoritesRepository(ManagementBookDatabase.getDatabase(context).bookFavoriteDao())
    }

    override val signedRepository: SignedRepository by lazy{
        OfflineSignedRepository(ManagementBookDatabase.getDatabase(context).signedDao())
    }
}
