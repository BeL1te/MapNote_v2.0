package com.be1te.big.notemap.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.be1te.big.notemap.db.firebase.FirebaseRepository
import com.be1te.big.notemap.db.room.NoteDatabase
import com.be1te.big.notemap.db.room.NoteRepository
import com.be1te.big.notemap.utilits.REPOSITORY
import com.be1te.big.notemap.utilits.TYPE_FIREBASE
import com.be1te.big.notemap.utilits.TYPE_ROOM
import com.be1te.big.notemap.utilits.doToast

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = NoteDatabase.getDatabase(mContext).noteDao()
                REPOSITORY = NoteRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY = FirebaseRepository()
                REPOSITORY.connectToDb({ onSuccess() }, { doToast(it)})
            }
        }
    }
}