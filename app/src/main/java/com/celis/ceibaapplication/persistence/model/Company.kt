package com.celis.ceibaapplication.persistence.model

import com.google.gson.annotations.Expose

data class Company(
    @Expose
    val name: String,
    @Expose
    val catchPhrase: String,
    @Expose
    val bs: String
)
