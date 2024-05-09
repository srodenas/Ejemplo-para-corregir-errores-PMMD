package com.example.srodenas.example_with_catalogs.ui.views.fragments.alerts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.srodenas.example_with_catalogs.R
import com.example.srodenas.example_with_catalogs.databinding.FragmentAlertsBinding
import com.example.srodenas.example_with_catalogs.ui.viewmodel.alerts.AlertsViewModel


class AlertsFragment : Fragment() {
    private var binding : FragmentAlertsBinding ? = null
    private val viewModelAlerts : AlertsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAlertsBinding.inflate(inflater, container, false)
        val root : View = binding!!.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = binding!!.textAlerts
        viewModelAlerts.text.observe(viewLifecycleOwner, {
            textView.text = it
        })

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}