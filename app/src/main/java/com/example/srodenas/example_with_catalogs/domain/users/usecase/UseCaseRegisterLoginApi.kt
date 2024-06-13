package com.example.srodenas.example_with_catalogs.domain.users.usecase

import com.example.srodenas.example_with_catalogs.domain.users.models.RepositoryUsers
import com.example.srodenas.example_with_catalogs.domain.users.models.User

class UseCaseRegisterLoginApi (val repositoryUsers: RepositoryUsers) {

    /*
    Hay que modificarlo, ya que debe informar si el usuario existe a la ui
     */
    suspend fun register(user: User) : Boolean {
        return repositoryUsers.register(user) //el que el usuario ya exista, lo debe tratar la api
       /* val posibleUser = repositoryUsers.isLoginApi(user.email, user.passw)
        if (posibleUser!= null){
            return repositoryUsers.register(user)
        }else {
            println("Ese usuario ya existe")
            return false
        }*/

    }

}