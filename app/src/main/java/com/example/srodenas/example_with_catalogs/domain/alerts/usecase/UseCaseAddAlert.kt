package com.example.srodenas.example_with_catalogs.domain.alerts.usecase

import com.example.srodenas.example_with_catalogs.domain.alerts.models.Alert
import com.example.srodenas.example_with_catalogs.domain.alerts.models.ListAlerts
import com.example.srodenas.example_with_catalogs.domain.alerts.models.RepositoryAlerts

/*
Caso de Uso añadir una alerta. Pasamos el repositorio.
 */
class UseCaseAddAlert(val repo : RepositoryAlerts) {

    suspend fun add(alert: Alert):Int{
        repo.addAlertForRepository(alert)
        ListAlerts.list.alerts.add(alert)//También actualizo en cache.
        return ListAlerts.list.alerts.lastIndex  //devuelvo la última posición insertada.

    }


}