package com.example.srodenas.example_with_catalogs.domain.alerts.models

import com.example.srodenas.example_with_catalogs.data.users.database.dao.AlertDao
import com.example.srodenas.example_with_catalogs.data.users.database.entities.AlertEntity
import com.example.srodenas.example_with_catalogs.domain.UserDataBaseSingleton
import com.example.srodenas.example_with_catalogs.domain.users.models.Profile

class RepositoryAlerts private constructor(private val alertDao : AlertDao){

    companion object{

        //objeto único RepositoryAlerts
        val repo : RepositoryAlerts by lazy{
            RepositoryAlerts(UserDataBaseSingleton.alertsDao)  //lo creamos dentro. singleton.
        }
    }

    suspend fun addAlertForRepository(newAlert : Alert){

        val idUser = Profile.profile.user.id //recupero los datos

        var alertEntity = AlertEntity(0,idUser, newAlert.textShort, newAlert.message, newAlert.alertDate)
        alertDao.insertAlert(alertEntity)  //insertamos la alerta.
    }




    suspend fun deleteAlertForRepository(alert : Alert){
        alertDao.deleteAlertById(alert.id)
    }



    //TODO no manejamos excepciones.
    suspend fun showAllAlerts(idUser: Int): List<Alert>{
        val listAlertEntity = alertDao.getAlertsForUser(idUser)

        return listAlertEntity.map{
            it.toEntity()  //mapeo a Alert cada AlertEntity
        }
    }


    //Sólo para el testeo. Debería estar en otra clase.
    suspend fun initAllAlertForTest(newAlert : Alert){
        for (i in 0..10)
            addAlertForRepository(newAlert)
    }



    //TODO no manejamos excepciones.
    suspend fun showAlertById(id : Int): Alert = (alertDao.getAlertById(id))!!.toEntity()


    fun AlertEntity.toEntity():Alert{
        return Alert(
            id = this.id,
            userId = this.userId,
            textShort = this.textShort,
            message = this.message,
            alertDate = this.alertDate
        )
    }

}