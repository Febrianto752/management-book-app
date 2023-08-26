package com.example.managebookapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "signed")
class Signed (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
){}
