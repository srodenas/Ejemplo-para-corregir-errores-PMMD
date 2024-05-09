package com.example.srodenas.example_with_catalogs.domain.users.models

/*
Nuestra lista de usuarios.
 */
class ListUsers private constructor(

){
    var users: MutableList<User> = mutableListOf()
    companion object{
        val list: ListUsers by lazy{
            ListUsers()
        }
    }
}