package com.be1te.big.notemap.screens.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.be1te.big.notemap.utilits.REPOSITORY

class ListNoteFragmentViewModel(application: Application): AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes
}