package com.example.srodenas.example_with_catalogs.ui.views.fragments.alerts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.srodenas.example_with_catalogs.R
import com.example.srodenas.example_with_catalogs.domain.users.models.User

class AdapterUser (
    var listUsers : MutableList<User>,
    val onDelete: (Int) -> Unit,
    val onDetails: (Int) -> Unit
): RecyclerView.Adapter<ViewHUser>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHUser {
        val layoutInflater = LayoutInflater.from(parent.context)//objeto para crear la vista.
        val layoutItemHotel = R.layout.item_user  //accedo al xml del item a crear.
        return ViewHUser(
            layoutInflater.inflate(layoutItemHotel, parent, false),
            onDelete,
            onDetails,
        )
    }



    override fun getItemCount(): Int = listUsers.size



    override fun onBindViewHolder(holder: ViewHUser, position: Int) {
        holder.renderize(listUsers.get(position), position)  //renderizamos la view.
    }
}