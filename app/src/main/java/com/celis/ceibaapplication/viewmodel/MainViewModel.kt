package com.celis.ceibaapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.celis.ceibaapplication.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    fun getUserList() = userRepository.getUsers()

}