package com.example.srodenas.example_with_catalogs.data.users.network.service

import com.example.srodenas.example_with_catalogs.data.users.network.models.request.RequestLoginUser
import com.example.srodenas.example_with_catalogs.data.users.network.models.responses.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiServiceInterface {

    @POST("auth")
    suspend fun login(@Body loginUser : RequestLoginUser) : Response<ResponseLogin>
}