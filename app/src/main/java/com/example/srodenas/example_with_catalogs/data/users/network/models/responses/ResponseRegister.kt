package com.example.srodenas.example_with_catalogs.data.users.network.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseRegister(
    @SerializedName("result")
    @Expose
    val result: String,

    @SerializedName("id")
    @Expose
    val id : Int
)   {


    }