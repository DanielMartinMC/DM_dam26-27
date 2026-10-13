package com.example.primerproyecto.domain.useCases

import com.example.primerproyecto.domain.model.Dispositivo

class DelDispositivoUseCase {

    fun delDespositivo(p: Dispositivo) = Dispositivo.del(p)
}