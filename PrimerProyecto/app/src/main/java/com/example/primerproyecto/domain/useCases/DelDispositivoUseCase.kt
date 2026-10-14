package com.example.primerproyecto.domain.useCases

import com.example.primerproyecto.data.Dispositivos
import com.example.primerproyecto.domain.model.Dispositivo

class DelDispositivoUseCase {

    fun delDispositivo(p: Dispositivo) = Dispositivos.del(p)
}