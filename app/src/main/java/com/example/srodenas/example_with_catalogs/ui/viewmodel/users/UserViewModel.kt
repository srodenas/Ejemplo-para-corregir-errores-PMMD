package com.example.srodenas.example_with_catalogs.ui.viewmodel.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.srodenas.example_with_catalogs.domain.users.models.ListUsers
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

    var usersLiveData= MutableLiveData<MutableList<User>>()
    var posNewUserlLiveData = MutableLiveData<Int>()
    var posDeleteHotelLiveDate = MutableLiveData<Int>()


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


    fun showUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            var data: MutableList<User>? = null

            if (ListUsers.list.users.isEmpty())
            //todo    data = getAllUsersDatabaseUseCase()  //recupera todos los usuarios de la BBDD
            else
                data = ListUsers.list.users

            data.let {
                withContext(Dispatchers.Main) {
                    usersLiveData.value = it
                }
            }
        }
    }

    fun addUser(user:User){
        viewModelScope.launch(Dispatchers.IO) {
            //todo newUserUseCase.setUser(user)
            var pos = 0
            //todo pos  = newUserUseCase()
             if ( pos != -1){
                /*
                Devuelve -1, si se cumplen las condiciones de insertar un nuevo usuario

                Lo que hacemos es al insertar un nuevo usuario, de la última posición del scroll (ultimo usuario)
                hacemos un desplazamiento de 20 para que veamos el nuevo pueblo.

               Según la documentación, es interesante que la actualización de los LiveData, se haga en el hilo principal.
                */
                withContext(Dispatchers.Main) {//Con Dispatchers.Main, indicamos que el hilo se ejecute en el principal.
                    posNewUserlLiveData.value = pos
                }
                showUsers()
            }




            fun deleteUser(pos : Int){
                //todo deleteUserUseCase.setPos(pos)
                //todo deleteUserUseCase()
                posDeleteHotelLiveDate.value = pos
                showUsers()
            }


            fun getUserForPosition(pos: Int) : User ?{
               //todo getHUserForPosUseCase.setPos(pos)
                val user =null
                //todo user = getHotelForPosUseCase()
                return user
            }

        }

    }

}








