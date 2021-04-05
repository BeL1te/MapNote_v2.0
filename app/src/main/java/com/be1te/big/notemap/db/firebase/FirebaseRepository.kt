package com.be1te.big.notemap.db.firebase

import androidx.lifecycle.LiveData
import com.be1te.big.notemap.db.DatabaseRepository
import com.be1te.big.notemap.db.room.Note
import com.be1te.big.notemap.utilits.EMAIL
import com.be1te.big.notemap.utilits.PASSWORD
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepository : DatabaseRepository {
    override val allNotes: LiveData<List<Note>>
        get() = TODO("Not yet implemented")

    private val mAuth = FirebaseAuth.getInstance()

    override suspend fun insert(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun connectToDb(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD).addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }
    }

    override fun signOut() {
        mAuth.signOut()
    }
}
