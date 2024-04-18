package com.example.srodenas.example_with_catalogs.domain.users.models

import android.content.Context
import androidx.room.Room
import com.example.srodenas.example_with_catalogs.data.users.database.UserDataBase
import com.example.srodenas.example_with_catalogs.data.users.database.dao.UserDao

object UserDataBaseSingleton {
    lateinit var database : UserDataBase
    lateinit var userDao : UserDao


    //Creo una instancia de Room
    fun init(context: Context){
        synchronized(this){
            if (database == null){
                database = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "my_app_user"

                ).build()
                userDao = database!!.userDao()
            }
        }
    }

}