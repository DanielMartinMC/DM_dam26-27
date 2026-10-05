package com.example.primerproyecto.data

import com.example.primerproyecto.domain.model.Dispositivo

object Dispositivos {


    private val dispositivos = mutableListOf(
        Dispositivo("Apple", "Iphone 17"),
        Dispositivo("Samsung", "Galaxy Z Fold8")

    )

    fun dameMovil() = dispositivos[1]

}