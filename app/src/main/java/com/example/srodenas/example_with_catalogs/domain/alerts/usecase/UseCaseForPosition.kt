package com.example.srodenas.example_with_catalogs.domain.alerts.usecase

import com.example.srodenas.example_with_catalogs.domain.alerts.models.Alert
import com.example.srodenas.example_with_catalogs.domain.alerts.models.RepositoryAlerts

class UseCaseForPosition (val repositoryAlerts: RepositoryAlerts){

    fun devAlert(pos : Int): Alert = repositoryAlerts.devAlertForPos(pos)

}