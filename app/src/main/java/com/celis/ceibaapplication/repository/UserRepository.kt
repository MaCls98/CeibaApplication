package com.celis.ceibaapplication.repository

import androidx.lifecycle.liveData
import com.celis.ceibaapplication.persistence.network.services.UserService
import kotlinx.coroutines.Dispatchers

class UserRepository(private val userService: UserService) {

    fun getUsers() = liveData(Dispatchers.IO){
        emit(userService.getUsers())
    }
}