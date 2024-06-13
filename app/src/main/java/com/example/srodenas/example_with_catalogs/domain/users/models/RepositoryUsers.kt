package com.example.srodenas.example_with_catalogs.domain.users.models

import com.example.srodenas.example_with_catalogs.data.users.database.dao.UserDao
import com.example.srodenas.example_with_catalogs.data.users.database.entities.UserEntity
import com.example.srodenas.example_with_catalogs.data.users.network.models.request.RequestLoginUser
import com.example.srodenas.example_with_catalogs.data.users.network.models.request.RequestRegister
import com.example.srodenas.example_with_catalogs.data.users.network.service.UserApiService
import com.example.srodenas.example_with_catalogs.framework.InstanceRetrofit
import com.example.srodenas.example_with_catalogs.framework.UserDataBaseSingleton


/*
Mezcla del Repository tanto para Room como para API
 */
class RepositoryUsers  private constructor(
    private val userDao : UserDao,
    val service: UserApiService
){
    companion object{

        val repo: RepositoryUsers by lazy {
            RepositoryUsers(
                UserDataBaseSingleton.userDao, //aquí va el dao para retrofit.
                UserApiService(InstanceRetrofit.getApiService())  //aquí va el servicio para api.
            )  //le pasamos el singleton. Aunque no será necesario.
        }
    }

    /*
    Logueo en Rooms
     */
    suspend fun isLoginEntity(email: String, password: String): User?{
        val posUser : UserEntity = userDao.login(email, password)
        var user : User ? = null
        if (posUser != null)
            user= User(posUser.id, posUser.name, posUser.password, posUser.email, posUser.phone, posUser.imag )
        return user
    }


    suspend fun registerEntity(user: User): Boolean{

        val exitUser = isLoginEntity(user.email, user.passw)
        if (exitUser == null){
            val userEntity = UserEntity(user.id, user.name, user.email, user.passw, user.phone, user.imagen)
            userDao.insertUser(userEntity)
            return true
        }else
            return false

    }

    /*
    Logueo en API
     */
    suspend fun isLoginApi(email: String, password: String) : User?{
        val userRequest = RequestLoginUser(email, password)
        val result = service.login(userRequest)
        result
            .onSuccess {  //caso satisfactorio de la llamada al servicio
               resUser->
                    val user = User(
                        resUser.id,
                        resUser.name,
                        resUser.email,
                        resUser.phone,
                        "",  //no hay imagen de momento
                        resUser.token
                    )
                    return user
            }
            .onFailure {
                println("${it.message}") //caso de cualquier error, mostramos el Runtime
            }
        return null
    }

    /*
    Se tiene en cuenta, que no se comprueba si ese usuario existe
    Habrá que modificar la api, para que antes de registrar, lo haga.
     */
    suspend fun register(user : User) : Boolean{
        val userRequest = RequestRegister(
            user.name,
            user.email,
            user.passw,
            user.phone
        )
        val result = service.register(userRequest)
        result
            .onSuccess {
                return true
             }
            .onFailure {
                println ("${it.message}")
            }
        return false  //posiblemente, usuario ya registrado.
    }


}