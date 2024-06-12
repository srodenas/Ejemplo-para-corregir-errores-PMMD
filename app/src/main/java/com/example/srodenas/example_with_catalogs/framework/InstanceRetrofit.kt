package com.example.srodenas.example_with_catalogs.framework

import com.example.srodenas.example_with_catalogs.data.users.network.service.UserApiServiceInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InstanceRetrofit {

    private const val URL_BASE_RETROFIT = "http://10.0.0.2.2/my_app_user"

    private val retrofit : Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(URL_BASE_RETROFIT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val service : UserApiServiceInterface by lazy{
        retrofit.create(UserApiServiceInterface::class.java)
    }

    fun getApiService(): UserApiServiceInterface = service


/*
  //Otra opción menos limpio
    private  var retrofit: Retrofit? = null
    private  var service: UserApiServiceInterface? = null

    fun init() : Retrofit{
        if (retrofit == null){
            retrofit = Retrofit
                .Builder()
                .baseUrl(URL_BASE_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
       return retrofit!!
    }

    fun getApiService() : UserApiServiceInterface{
        if (service == null){
            service = init().create(UserApiServiceInterface::class.java)
        }
        return service!!
    }

 */
}