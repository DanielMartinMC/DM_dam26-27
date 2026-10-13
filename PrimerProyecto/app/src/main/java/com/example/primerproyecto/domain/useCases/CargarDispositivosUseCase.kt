package com.example.primerproyecto.domain.useCases

import com.example.primerproyecto.data.Dispositivos

class CargarDispositivosUseCase {

    fun cargarLista() = Dispositivos.dameLista()
}