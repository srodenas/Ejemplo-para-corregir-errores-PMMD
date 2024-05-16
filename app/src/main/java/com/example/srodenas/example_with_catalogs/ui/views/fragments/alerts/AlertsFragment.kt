package com.example.srodenas.example_with_catalogs.ui.views.fragments.alerts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.srodenas.example_with_catalogs.R
import com.example.srodenas.example_with_catalogs.databinding.FragmentAlertsBinding
import com.example.srodenas.example_with_catalogs.domain.alerts.models.Alert
import com.example.srodenas.example_with_catalogs.ui.viewmodel.alerts.AlertsViewModel
import com.example.srodenas.example_with_catalogs.ui.views.fragments.alerts.adapter.AdapterAlerts


class AlertsFragment : Fragment() {
    private var binding : FragmentAlertsBinding ? = null
    private val viewModelAlerts : AlertsViewModel by viewModels()
    private lateinit var layoutManager: LinearLayoutManager  //gestor del layout
    lateinit var adapterAlerts : AdapterAlerts  //Adaptador de Alertas


    //Método que es llamado al crear el fragmento.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Necesitamos inicializar el Adaptador, de momento con una lista vacía
        adapterAlerts = AdapterAlerts(
            mutableListOf(),
            {
                //Borrar
                deleteAlertForDialog(it)

            },{
                //Detalles
                detailsAlert(it)

            }
        )
    }

    //Crea el DialogFragment para el borrado personalizado
    private fun deleteAlertForDialog(pos: Int) {
        //TODO Creamos el DialogoFragment, pasamos la posición a borrar, el nombre de la alerta y una lamda con la ejecución Afirmativa.
        /*
            //TODO la idea es iniciar el DialogoFragment y al pulsar en OK, invocar a la lamda.
            TODO cuando ejecutemos la logica de la lamda, el viewmodel, llamara al caso de uso que me devuelve la Alerta de dicha posición en cache
            TODO Por último, invocamos al método que ejecutará el borrado de dicha alerta, a partir del método okOnDeleteAlert(pos)
                val dialog = DialogDeleteAlert(
                pos,
                viewModelAlerts.getAlertForPosition(pos).name
                ){
                    position -> okOnDeleteAlert(position)
                }
                dialog.show(requireActivity().supportFragmentManager, "Borraremos la alerta en posición $pos")
         */

    }

    private fun okOnDeleteAlert(pos: Int){
        viewModelAlerts.delAlert(pos) //Eliminamos la alerta.
    }




    //Muestra los detalles de la Alerta
    private fun detailsAlert(pos:Int){
        val navController = this.findNavController()
        navController.navigate(AlertsFragmentDirections.actionAlertsFragmentToDetailsAlertFragment(num = pos))
    }

    //Método que es llamada para crear la vista del fragmento.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos la vista del fragmento
        binding = FragmentAlertsBinding.inflate(inflater, container, false)
        val root : View = binding!!.root
        return root
    }


    /*
    Método que es llamado despues de que se cree la vista del fragmento.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding!!.myRecyclerViewAlerts.layoutManager = LinearLayoutManager(activity) //Configuramos el gestor del Recycler como LinearLayout
        layoutManager = binding!!.myRecyclerViewAlerts.layoutManager as LinearLayoutManager  //Nos guardamos el gestor del LinearLayout del ReciclerView
        binding!!.myRecyclerViewAlerts.adapter = adapterAlerts //cargamos el adaptador en la vista.
        binding!!.btnAdd.setOnClickListener {
            addAlert()
        }
        setObserverChangeViewModel()  //Método que gestiona cualquier cambio en los modelos.


    }

    //TODO Método que añade una alerta nueva
    private fun addAlert() {
        TODO("Not yet implemented")
            /*
            TODO CREAMOS EL DIALOG FRAGMENT PARA LA INSERCIÓN Y PASAMOS UNA LAMDA
            TODO DENTRO DE LA LAMDA, QUE DEVOLVERÁ LOS DATOS DE LA NUEVA ALERTA,
            TODO INVOCAMOS AL MÉTODO QUE REALIZARÁ LA INSERCIÓN REAL.
               Toast.makeText(context, "Añadiremos un nuevo hotel", Toast.LENGTH_LONG).show()
                    val dialog = DialogNewHAlert(){
                    alert -> okOnNewAlert(alert)
                }
                val contexto = requireActivity()
                dialog.show(contexto.supportFragmentManager, "Añadimos un nueva alertal")
             */

    }

    private fun okOnNewAlert(newAlert : Alert){
        viewModelAlerts.addAlerts(newAlert)

    }

    /*
    Método que gestiona todos los cambios, tanto al añadir, mostrar como eliminar cualquier modelo.
     */
    private fun setObserverChangeViewModel() {

        viewModelAlerts.listAlertsLiveData.observe(viewLifecycleOwner,{
            listAlert->
                adapterAlerts.notifyDataSetChanged()   //Debemos notificar al adaptador del cambio en la ui
        })
        viewModelAlerts.posNewAlertLiveDate.observe(viewLifecycleOwner,{
            posNewAlert ->
                adapterAlerts.notifyItemInserted(posNewAlert)
                layoutManager.scrollToPositionWithOffset(posNewAlert, 20)
        })

        viewModelAlerts.posDeleteAlertLiveData.observe(viewLifecycleOwner,{
            posDeleteAlert ->
                adapterAlerts.notifyItemRemoved(posDeleteAlert)
                layoutManager.scrollToPositionWithOffset(posDeleteAlert, 20)
        })

    }

    //Método que es llamado para devolver en nuestra propiedad, el gestor del Linear del RecyclerView.

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}