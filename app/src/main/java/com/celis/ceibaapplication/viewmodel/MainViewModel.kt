package com.celis.ceibaapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.celis.ceibaapplication.persistence.model.User
import com.celis.ceibaapplication.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    val userList = arrayListOf<User>()

    fun insertUsersIntoDB(users: List<User>) = CoroutineScope(Dispatchers.IO).launch {
        userRepository.insertUsersIntoDB(users)
        userList.addAll(users)
    }

    fun getUserListFromApi() = userRepository.getUserListFromApi()

    fun getUserListFromDB() = userRepository.getUserListFromDB()

    fun getPostsByUserId(userId: Int) = userRepository.getPostsByUserId(userId)

}