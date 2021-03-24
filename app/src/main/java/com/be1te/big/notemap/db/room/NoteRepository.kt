package com.be1te.big.notemap.db.room

import androidx.lifecycle.LiveData
import com.be1te.big.notemap.db.DatabaseRepository

class NoteRepository(private val noteDao: NoteDao) : DatabaseRepository {
    override val allNotes: LiveData<List<Note>>
        get() = noteDao.readAllNotes()

    override suspend fun insert(note: Note, onSuccess: () -> Unit) {
        noteDao.insertNote(note)
        onSuccess()
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteDao.deleteNote(note)
        onSuccess()
    }
}