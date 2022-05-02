package com.celis.ceibaapplication.persistence.model

import com.google.gson.annotations.Expose

data class Post(
    @Expose
    val id: Int,
    @Expose
    val userId: Int,
    @Expose
    val title: String,
    @Expose
    val body: String
)
