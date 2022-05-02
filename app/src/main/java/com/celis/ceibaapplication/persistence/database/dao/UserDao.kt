package com.celis.ceibaapplication.persistence.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.celis.ceibaapplication.persistence.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(usersToInsert: List<User>)
}