package com.be1te.big.notemap.utilits

import com.be1te.big.notemap.MainActivity
import com.be1te.big.notemap.db.DatabaseRepository

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"