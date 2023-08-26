package com.example.managebookapp.data

import androidx.room.Embedded
import androidx.room.Relation

class BookFavoriteUser (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val bookFavorites: List<BookFavorite>
)
