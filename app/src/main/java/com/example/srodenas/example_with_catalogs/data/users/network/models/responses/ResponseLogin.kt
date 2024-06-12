package com.example.srodenas.example_with_catalogs.data.users.network.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("result")
    @Expose
    val result: String,

    @SerializedName("token")
    @Expose
    val token : String,

    @SerializedName("id")
    @Expose
    val id : Int,

    @SerializedName("nombre")
    @Expose
    val name : String,

    @SerializedName("email")
    @Expose
    val email : String,

    @SerializedName("telefono")
    @Expose
    val phone : String
) {}