package com.example.primerproyecto.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.primerproyecto.domain.model.Dispositivo
import com.example.primerproyecto.domain.useCases.AddDispositivoUseCase
import com.example.primerproyecto.domain.useCases.CargarDispositivosUseCase
import com.example.primerproyecto.domain.useCases.DameMovilUseCase
import com.example.primerproyecto.domain.useCases.DelDispositivoUseCase

class MainViewModel(
    private val dameMovilUseCase: DameMovilUseCase,
    private val cargarDispositivosUseCase: CargarDispositivosUseCase,
    private val addDispositivoUseCase: AddDispositivoUseCase,
    private val delDispositivoUseCase: DelDispositivoUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<MainState>(MainState("inicio", listOf()))

    val state: LiveData<MainState> = _state

    init {
        handleCargaLista()
    }

    fun handleDameMovil() {
        val mov = dameMovilUseCase.dameMovil()
        _state.value = _state.value?.copy(movil = mov.toString()) ?: MainState(mov.toString(), listOf())
    }

    fun handleCargaLista() {
        _state.value = _state.value?.copy(dispositivos = cargarDispositivosUseCase.cargarLista())
    }

    fun handleAdd(p: Dispositivo) {
        try {
            addDispositivoUseCase.addDispositivo(p)
            _state.value = _state.value?.copy(dispositivos = cargarDispositivosUseCase.cargarLista())
        } catch (e: Exception) {
            _state.value = _state.value?.copy(error = e.message)
        }
    }

    fun handleDelete(p: Dispositivo) {
        try {
            delDispositivoUseCase.delDispositivo(p)
            _state.value = _state.value?.copy(dispositivos = cargarDispositivosUseCase.cargarLista())
        } catch (e: Exception) {
            _state.value = _state.value?.copy(error = e.message)
        }
    }

    fun handleLimpiarError() {
        _state.value = _state.value?.copy(error = null)
    }
}

class MainViewModelFactory(
    private val dameMovilUseCase: DameMovilUseCase,
    val cargarDispositivosUseCase: CargarDispositivosUseCase,
    val addDispositivoUseCase: AddDispositivoUseCase,
    val delDispositivoUseCase: DelDispositivoUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return MainViewModel(
                dameMovilUseCase,
                cargarDispositivosUseCase,
                addDispositivoUseCase,
                delDispositivoUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")}
}