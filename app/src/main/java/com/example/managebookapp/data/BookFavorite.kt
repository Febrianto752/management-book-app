package com.example.managebookapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book_favorites")
class BookFavorite (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cover: Int,
    val author: String,
    val title: String,
    val description: String,
    val userId: Int
){}



