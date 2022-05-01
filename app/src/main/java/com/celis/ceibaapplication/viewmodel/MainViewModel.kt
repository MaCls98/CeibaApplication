package com.celis.ceibaapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.celis.ceibaapplication.persistence.model.User
import com.celis.ceibaapplication.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    val userList = arrayListOf<User>()

    fun getUserList() = userRepository.getUsers()

}