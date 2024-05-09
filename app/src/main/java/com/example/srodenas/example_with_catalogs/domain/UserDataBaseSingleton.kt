package com.example.srodenas.example_with_catalogs.domain

import android.content.Context
import androidx.room.Room
import com.example.srodenas.example_with_catalogs.data.users.database.UserDataBase
import com.example.srodenas.example_with_catalogs.data.users.database.dao.AlertDao
import com.example.srodenas.example_with_catalogs.data.users.database.dao.UserDao

object UserDataBaseSingleton {
    var database : UserDataBase? = null
    lateinit var userDao : UserDao
    lateinit var alertsDao : AlertDao


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
                alertsDao = database!!.alertsDao()
            }
        }
    }

}