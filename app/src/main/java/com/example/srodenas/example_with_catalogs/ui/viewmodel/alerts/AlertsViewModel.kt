package com.example.srodenas.example_with_catalogs.ui.viewmodel.alerts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlertsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Alertas"
    }
    val text : LiveData<String> = _text  //recordamos que LiveData es una clase Abstracta.
}