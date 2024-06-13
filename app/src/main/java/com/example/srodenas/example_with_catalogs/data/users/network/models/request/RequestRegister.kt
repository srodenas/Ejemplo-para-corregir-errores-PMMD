package com.example.srodenas.example_with_catalogs.data.users.network.models.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestRegister(

    @SerializedName("nombre")
    @Expose
    val name : String,

    @SerializedName("email")
    @Expose
    val email : String,

    @SerializedName("password")
    @Expose
    val passw : String,


    @SerializedName("telefono")
    @Expose
    val phone : String

){}
