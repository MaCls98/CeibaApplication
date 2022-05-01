package com.celis.ceibaapplication.persistence.model

import com.google.gson.annotations.Expose

data class Address(
    @Expose
    val street: String,
    @Expose
    val suite: String,
    @Expose
    val city: String,
)
