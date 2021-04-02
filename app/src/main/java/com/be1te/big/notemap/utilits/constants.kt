package com.be1te.big.notemap.utilits

import android.annotation.SuppressLint
import android.widget.Toast
import com.be1te.big.notemap.MainActivity
import com.be1te.big.notemap.db.DatabaseRepository
import java.text.SimpleDateFormat
import java.util.Date

import kotlin.properties.Delegates

fun doToast(text: String) {
    Toast.makeText(APP_ACTIVITY, text, Toast.LENGTH_SHORT).show()
}

@SuppressLint("SimpleDateFormat")
fun currentData() : String {
    val formatDate = SimpleDateFormat("dd.MM.yyyy")
    return formatDate.format(Date())
}

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository
var COORDINATE_X by Delegates.notNull<Float>()
var COORDINATE_Y by Delegates.notNull<Float>()
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
