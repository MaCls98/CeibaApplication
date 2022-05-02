package com.celis.ceibaapplication.persistence.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey
    @Expose
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val username: String,
    @Expose
    val email: String,
    @Expose
    val phone: String,
    @Expose
    val website: String,
) : Parcelable {
    @IgnoredOnParcel
    @Ignore
    @Expose
    val address: Address? = null

    @IgnoredOnParcel
    @Ignore
    @Expose
    val company: Company? = null
}
