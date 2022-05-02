package com.celis.ceibaapplication.repository

import androidx.lifecycle.liveData
import com.celis.ceibaapplication.persistence.database.dao.UserDao
import com.celis.ceibaapplication.persistence.model.User
import com.celis.ceibaapplication.persistence.network.services.UserService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao) {

    fun insertUsersIntoDB(users: List<User>): Unit = userDao.insertUsers(users)

    fun getUserListFromDB() = liveData(Dispatchers.IO) {
        emit(userDao.getAllUsers())
    }

    fun getUserListFromApi() = liveData(Dispatchers.IO){
        emit(userService.getUsers())
    }

    fun getPostsByUserId(userId: Int) = liveData(Dispatchers.IO) {
        emit(userService.getPostsByUserId(userId))
    }
}