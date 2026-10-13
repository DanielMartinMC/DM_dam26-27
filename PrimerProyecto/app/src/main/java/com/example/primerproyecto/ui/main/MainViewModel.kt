package com.example.primerproyecto.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.primerproyecto.domain.useCases.AddDispositivoUseCase
import com.example.primerproyecto.domain.useCases.CargarDispositivosUseCase
import com.example.primerproyecto.domain.useCases.DameMovilUseCase
import com.example.primerproyecto.domain.useCases.DelDispositivoUseCase

class MainViewModel(
    private val dameMovilUseCase: DameMovilUseCase,
    private val cargarDispositivosUseCase: CargarDispositivosUseCase,
    private val addDispositivoUseCase: AddDispositivoUseCase,
    private val delDispositivoUseCase: DelDispositivoUseCase,

) : ViewModel(){

    private val _state = MutableLiveData<MainState>(MainState("inicio",listOf()))


    val state : LiveData<MainState> = _state

    init {
        handleCargaLista()

    }


    fun handleDameMovil(): Unit {

        var mov = dameMovilUseCase.dameMovil()


        _state.value = _state.value?.copy(movil = mov.toString()) ?: MainState(mov.toString())
    }

    fun handleCargaLista(){
        _state.value = _state.value?.copy(moviles = cargarDispositivosUseCase.cargarLista())
    }

    class MainViewModelFactory(private val dameMovilUseCase: DameMovilUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(dameMovilUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")

        }
    }

}