package com.example.srodenas.example_with_catalogs.domain.users.models

import com.example.srodenas.example_with_catalogs.data.users.database.dao.UserDao
import com.example.srodenas.example_with_catalogs.data.users.database.entities.UserEntity
import com.example.srodenas.example_with_catalogs.domain.UserDataBaseSingleton


class Repository  private constructor(private val userDao : UserDao){
    companion object{
        val repo: Repository by lazy {
            Repository(UserDataBaseSingleton.userDao)  //le pasamos el singleton. Aunque no será necesario.
        }
    }

    suspend fun isLoginEntity(email: String, passord: String): User?{
        val posUser : UserEntity = userDao.login(email, passord)
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
}