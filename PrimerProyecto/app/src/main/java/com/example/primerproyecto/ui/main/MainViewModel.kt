package com.example.primerproyecto.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.primerproyecto.domain.useCases.DameMovilUseCase

class MainViewModel(val dameMovilUseCase: DameMovilUseCase) : ViewModel(){

    private val _state = MutableLiveData<MainState>(MainState("inicio"))


    val state : LiveData<MainState> = _state


    fun handleDameMovil(): Unit {

        var mov = dameMovilUseCase.dameMovil()


        _state.value = _state.value?.copy(movil = mov.toString()) ?: MainState(mov.toString())
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