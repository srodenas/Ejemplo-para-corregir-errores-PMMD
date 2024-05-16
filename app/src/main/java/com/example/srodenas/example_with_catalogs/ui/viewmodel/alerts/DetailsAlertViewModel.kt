package com.example.srodenas.example_with_catalogs.ui.viewmodel.alerts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.srodenas.example_with_catalogs.domain.alerts.models.Alert


//TODO
class DetailsAlertViewModel : ViewModel() {
    var alertLiveData = MutableLiveData<Alert>()  //Para notificar que ya he recuperado la alerta.

    //TODO
    /*fun devAlertForPos(pos : Int):Alert{

    }

     */

}