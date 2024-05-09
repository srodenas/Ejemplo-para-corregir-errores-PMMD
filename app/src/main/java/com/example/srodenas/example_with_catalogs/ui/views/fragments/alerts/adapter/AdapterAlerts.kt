package com.example.srodenas.example_with_catalogs.ui.views.fragments.alerts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.srodenas.example_with_catalogs.R
import com.example.srodenas.example_with_catalogs.domain.alerts.models.Alert

class AdapterAlerts   (
    var listAlerts : MutableList<Alert>,
    val onDelete: (Int) -> Unit,
    val onDetails: (Int) -> Unit
): RecyclerView.Adapter<ViewHAlert>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHAlert {
        val layoutInflater = LayoutInflater.from(parent.context)//objeto para crear la vista.
        val layoutItemAlert = R.layout.item_alert  //accedo al xml del item a crear.
        return ViewHAlert(
            layoutInflater.inflate(layoutItemAlert, parent, false),
            onDelete, onDetails
        )
    }



    override fun getItemCount(): Int = listAlerts.size



    override fun onBindViewHolder(holder: ViewHAlert, position: Int) {
        holder.renderize(listAlerts.get(position), position)  //renderizamos la view.
    }
}