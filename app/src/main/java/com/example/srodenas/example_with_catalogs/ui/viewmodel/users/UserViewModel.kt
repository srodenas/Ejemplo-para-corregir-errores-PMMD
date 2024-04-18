package com.example.srodenas.example_with_catalogs.ui.viewmodel.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.srodenas.example_with_catalogs.domain.users.models.Repository
import com.example.srodenas.example_with_catalogs.domain.users.models.User
import com.example.srodenas.example_with_catalogs.domain.users.usecase.UseCaseLogin
import com.example.srodenas.example_with_catalogs.domain.users.usecase.UseCaseRegisterLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserViewModel (): ViewModel() {
    val login = MutableLiveData<User?>()
    val repository = Repository.repo  //Aquí tengo la instancia del repositorio
    val useCaseLogin = UseCaseLogin(repository)
    val useCaseRegisterLogin = UseCaseRegisterLogin(repository)
    val register = MutableLiveData<Boolean>()



    /*
1.- La consulta de acceso a datos asíncrona, la haremos en un hilo diferente al principal. Bien utilizar el de E/S.
2.- Las actualizaciones, las haremos en el mismo hilo principal. Todo lo que tenga que ver con LiveData.
 */

    fun isLogin(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {//esta corrutina, se ejecuta en el hilo de E/S, no en el principal.
            val user: User? = null
            if (!email.isEmpty() && !password.isEmpty()) {
                val user = useCaseLogin.login(email, password) //invocamos al usuario.
            }
            /*
                Según la documentación, es interesante que la actualización de los LiveData, se haga en el hilo principal.
                 */
            withContext(Dispatchers.Main) {//Con Dispatchers.Main, indicamos que el hilo se ejecute en el principal.
                login.postValue(user)
            }
        }
    }



    fun register(user : User){
        var isReg = false

        //Aquí es cuando invocamos a la data para su persistencia.
        viewModelScope.launch(Dispatchers.IO) {
            isReg  = useCaseRegisterLogin.register(user)  //registramos el usaurio
            withContext(Dispatchers.Main) {
                register.value = isReg
            }
        }
    }

}








