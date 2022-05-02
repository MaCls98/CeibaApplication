package com.celis.ceibaapplication.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.celis.ceibaapplication.persistence.database.dao.UserDao
import com.celis.ceibaapplication.persistence.model.User

@Database(entities = [User::class], version = 1)
abstract class CeibaDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "ceiba_database"
    }
}