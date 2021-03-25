package com.be1te.big.notemap.db.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String,
    var date: String,
    var coordinatesX: String,
    var coordinatesY: String,
    var content: String
) : Serializable {
    override fun toString(): String {
        return "$title $coordinatesX $coordinatesY $content $date"
    }
}