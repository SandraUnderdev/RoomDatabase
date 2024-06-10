package com.example.jetpackroomdatabase.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class Note(
   //@ColumnInfo(name = "id_2") used to rename column
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val noteName: String,
    val noteContent: String
)
