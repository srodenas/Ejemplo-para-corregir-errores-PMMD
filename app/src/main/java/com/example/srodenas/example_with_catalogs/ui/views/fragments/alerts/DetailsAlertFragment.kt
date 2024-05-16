package com.example.srodenas.example_with_catalogs.ui.views.fragments.alerts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.srodenas.example_with_catalogs.databinding.FragmentAlertsBinding
import com.example.srodenas.example_with_catalogs.ui.viewmodel.alerts.DetailsAlertViewModel


class DetailsAlertFragment : Fragment() {
    lateinit var binding : FragmentAlertsBinding
    val myArgument : DetailsAlertFragmentArgs by navArgs()
    private val viewModelAlertDetails : DetailsAlertViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAlertsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        requireActivity().let{
            val posAlertDetail = myArgument.num //Ya tengo el argumento pasado desde el otro fragmento
            /*
            1.- Sólo tengo que recuperar dicho objeto desde el viewmodel. Invoco al método oportuno
            2.- Observo cambios en el modelo
            3.- Muestro los detalles en la view.

             */

            viewModelAlertDetails.alertLiveData.observe(viewLifecycleOwner, {
                alert -> //TODO YA TENGO LA ALERTA Y TENGO QUE MOSTRARLA EN LA UI
            })

        }

    }

}