package com.example.jetpackroomdatabase

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.example.jetpackroomdatabase.db.Note
import com.example.jetpackroomdatabase.db.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NotesAdapter.ClickListener {
    private lateinit var repo: Repo
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesViewModelFactory: NotesViewModelFactory
    private lateinit var notesDatabase: NoteDatabase
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var rv: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var dialog: Dialog
    private lateinit var edtNoteName: EditText
    private lateinit var edtNoteContent: EditText
    private lateinit var btnSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        init()

        notesViewModel.getAllNotes().observe(this) {
            notesAdapter = NotesAdapter(it, this)
            rv.adapter = notesAdapter
            rv.layoutManager = LinearLayoutManager(this)
        }

        fab.setOnClickListener{
            openDialog(comingFromFab = true)
        }



     /*   notesViewModel.insert(
            Note(
                noteName = "Some note",
                noteContent = "This is the content of note"
            )
        )
        notesViewModel.insert(
            Note(
                noteName = "Some note 2",
                noteContent = "This is the content of note 2"
            )
        )*/
    }

    private fun openDialog(comingFromFab: Boolean) {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.layout_dialog)

        edtNoteName = dialog.findViewById(R.id.edt_note_name)
        edtNoteContent = dialog.findViewById(R.id.edt_note_content)
        btnSave = dialog.findViewById(R.id.btn_sabe)

        btnSave.setOnClickListener{
            val note = Note(
                noteName = edtNoteName.text.toString() ,
                noteContent = edtNoteContent.text.toString()
            )
            if(comingFromFab) {
                notesViewModel.insert(note)
            }else{
                notesViewModel.update(note)
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun init(){
        notesDatabase = NoteDatabase(this)
        repo = Repo(notesDatabase.getNoteDao())
        notesViewModelFactory = NotesViewModelFactory(repo)
        notesViewModel = ViewModelProvider(this, notesViewModelFactory).get(NotesViewModel::class.java)
        rv = findViewById(R.id.rv)
        fab = findViewById(R.id.fab)
    }

    override fun updateNote(note: Note) {
       openDialog(comingFromFab = false)
    }

    override fun delete(note: Note) {
        notesViewModel.delete(note)
    }
}