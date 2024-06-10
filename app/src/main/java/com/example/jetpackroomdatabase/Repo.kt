package com.example.jetpackroomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetpackroomdatabase.db.Dao
import com.example.jetpackroomdatabase.db.Note

class Repo(private val dao: Dao) {

    fun insert(note: Note) {
        dao.insert(note)
    }

    fun delete(note: Note){
        dao.delete(note)
    }

    fun update(note: Note){
        dao.update(note)
    }

    fun getAllNotes() = dao.getAllNotes()
}