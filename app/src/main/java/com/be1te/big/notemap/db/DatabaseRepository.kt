package com.be1te.big.notemap.db

import androidx.lifecycle.LiveData
import com.be1te.big.notemap.db.room.Note

interface DatabaseRepository {
    val allNotes: LiveData<List<Note>>
    suspend fun insert(note: Note, onSuccess: () -> Unit)
    suspend fun delete(note: Note, onSuccess: () -> Unit)

    fun connectToDb(onSuccess: () -> Unit, onFail: (String) -> Unit) {}

    fun signOut() {}
}