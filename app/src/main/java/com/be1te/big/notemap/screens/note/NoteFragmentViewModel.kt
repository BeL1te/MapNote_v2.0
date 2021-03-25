package com.be1te.big.notemap.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.be1te.big.notemap.db.room.Note
import com.be1te.big.notemap.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application) : AndroidViewModel(application) {
    fun delete(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            REPOSITORY.delete(note) {
                onSuccess()
            }
        }
    }
}