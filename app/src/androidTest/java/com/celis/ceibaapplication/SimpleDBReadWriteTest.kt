package com.celis.ceibaapplication

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.celis.ceibaapplication.persistence.database.CeibaDatabase
import com.celis.ceibaapplication.persistence.database.dao.UserDao
import com.celis.ceibaapplication.persistence.model.User
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class SimpleDBReadWriteTest {
    private lateinit var userDao: UserDao
    private lateinit var db: CeibaDatabase

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            CeibaDatabase::class.java
        ).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndTestInsertions(){
        val userList = arrayListOf<User>()
        for (i in 0 until 3){
            userList.add(
                User(
                    i, "user $i", "u_$i", "mail@mail.mail", "321", "abc.com"
                )
            )
        }
        userDao.insertUsers(userList)
        assert(userDao.getAllUsers().size == 3)
    }
}