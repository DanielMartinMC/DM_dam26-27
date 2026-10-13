package com.example.primerproyecto.domain.useCases

import com.example.primerproyecto.data.Dispositivos
import com.example.primerproyecto.domain.model.Dispositivo

class AddDispositivoUseCase {

    fun addDispositivo(p: Dispositivo) = Dispositivos.add(p)
}