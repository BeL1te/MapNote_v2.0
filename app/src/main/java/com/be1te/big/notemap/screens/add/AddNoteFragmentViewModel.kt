package com.be1te.big.notemap.screens.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.be1te.big.notemap.R
import com.be1te.big.notemap.db.room.Note
import com.be1te.big.notemap.utilits.APP_ACTIVITY
import com.be1te.big.notemap.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteFragmentViewModel(application: Application) : AndroidViewModel(application) {
    fun insert(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            REPOSITORY.insert(note) {
                onSuccess()
            }
        }
    }

    fun setTitle() {
        APP_ACTIVITY.title = "Add Note"
    }
}