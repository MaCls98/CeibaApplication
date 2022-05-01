package com.celis.ceibaapplication.persistence.model

import com.google.gson.annotations.Expose

data class User(
    @Expose
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val username: String,
    @Expose
    val email: String,
    @Expose
    val address: Address,
    @Expose
    val phone: String,
    @Expose
    val website: String,
    @Expose
    val company: Company
)
