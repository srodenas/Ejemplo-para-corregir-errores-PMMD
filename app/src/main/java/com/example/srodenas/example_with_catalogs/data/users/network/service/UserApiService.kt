package com.example.srodenas.example_with_catalogs.data.users.network.service

import com.example.srodenas.example_with_catalogs.data.users.network.models.request.RequestLoginUser
import com.example.srodenas.example_with_catalogs.data.users.network.models.request.RequestRegister
import com.example.srodenas.example_with_catalogs.data.users.network.models.responses.ResponseLogin
import com.example.srodenas.example_with_catalogs.data.users.network.models.responses.ResponseRegister
import retrofit2.Response


//Clase encargada de crear los servicios del usuario
class UserApiService (val userApiServiceInterface : UserApiServiceInterface){

    suspend fun login( user : RequestLoginUser) : Result<ResponseLogin>{
        try{
            val response : Response<ResponseLogin> = userApiServiceInterface.login(user)

            if (response.isSuccessful){
                return response.body()?.let {
                    Result.success(it)
                }?: Result.failure(RuntimeException("Respuesta usuarios nula"))

            }else{
                return Result.failure(RuntimeException("Error en la llamada y sin respuesta"))  //me creo una excepción como respuesta fallida.
            }

        }catch (e : Exception){
            return Result.failure(e)
        }
    }


    suspend fun register(user: RequestRegister) : Result<ResponseRegister>{
        try {
           val response = userApiServiceInterface.register(user)
            if (response.isSuccessful){
                return response.body()?.let {
                    Result.success(it)
                }?:  Result.failure(RuntimeException("Respuesta de registro nula"))
            }else{
                return Result.failure(RuntimeException("No hay respuesta en el registro"))
            }
        }catch (e : Exception){
            return Result.failure(e)
        }
    }



}