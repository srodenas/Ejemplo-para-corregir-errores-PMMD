package com.example.srodenas.example_with_catalogs.data.users.network.models.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestLoginUser(
    @SerializedName("email")
    @Expose
    val email : String,

    @SerializedName("password")
    @Expose
    val passw: String

) {}