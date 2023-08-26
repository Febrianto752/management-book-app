package com.example.managebookapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class, BookFavorite::class, Signed::class], version = 1, exportSchema = false)
abstract class ManagementBookDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun bookFavoriteDao(): BookFavoriteDao

    abstract fun signedDao() : SignedDao

    companion object {
        @Volatile
        private var Instance: ManagementBookDatabase? = null

        fun getDatabase(context: Context): ManagementBookDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ManagementBookDatabase::class.java, "book_database")
                    .build().also { Instance = it }
            }
        }
    }
}
