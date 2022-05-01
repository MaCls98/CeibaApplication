package com.celis.ceibaapplication.persistence.network.services

import com.celis.ceibaapplication.persistence.model.User
import com.celis.ceibaapplication.persistence.network.NetworkConstants
import retrofit2.http.GET

interface UserService {

    @GET(NetworkConstants.GET_USERS)
    suspend fun getUsers(): List<User>
}