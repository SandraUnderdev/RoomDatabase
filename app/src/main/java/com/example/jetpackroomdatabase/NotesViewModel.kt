package com.example.jetpackroomdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackroomdatabase.db.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(private val repo: Repo) : ViewModel() {
    fun getAllNotes() = repo.getAllNotes()

    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.IO){
            repo.insert(note)
        }
    }

    fun delete(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(note)
        }
    }

    fun update(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.update(note)
        }
    }

}