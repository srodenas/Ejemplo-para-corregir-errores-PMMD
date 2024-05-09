package com.example.srodenas.example_with_catalogs.ui.views.fragments.alerts.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.srodenas.example_with_catalogs.databinding.ItemUserBinding
import com.example.srodenas.example_with_catalogs.domain.users.models.User

class ViewHUser (view: View,
                 val onDelete: (Int) -> Unit,
                 val onDetails: (Int) -> Unit

) : RecyclerView.ViewHolder(view){

    lateinit var binding: ItemUserBinding
    init {
        binding = ItemUserBinding.bind(view)
    }


    fun renderize(user: User, position:Int){
        /*
        Debemos de setear los campos
         */
        binding.btnDelete.setOnClickListener{
            onDelete(position)
        }

        binding.btnDetail.setOnClickListener{
            onDetails(position)
        }

    }
}