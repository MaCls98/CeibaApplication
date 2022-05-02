package com.celis.ceibaapplication.persistence.network.services

import com.celis.ceibaapplication.persistence.model.Post
import com.celis.ceibaapplication.persistence.model.User
import com.celis.ceibaapplication.persistence.network.NetworkConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET(NetworkConstants.GET_USERS)
    suspend fun getUsers(): List<User>

    @GET(NetworkConstants.GET_POSTS)
    suspend fun getPostsByUserId(
        @Query("userId") userId: Int
    ): List<Post>
}