package com.example.primerproyecto.data

import com.example.primerproyecto.domain.model.Dispositivo
import kotlin.random.Random

object Dispositivos {


    private val dispositivos = mutableListOf(
        Dispositivo("Apple", "Iphone 17"),
        Dispositivo("Samsung", "Galaxy Z Fold8")

    )

    fun dameMovil() = dispositivos[1]

    fun dameLista() = dispositivos.toList()
    fun add(p: Dispositivo) {
        if (Random.nextDouble()> 0.60)
            throw RuntimeException("Error al añadir")
        dispositivos.add(p)
    }

    fun del(p: Dispositivo) {
        if ( Random.nextDouble()> 0.40)
            throw RuntimeException("Error al borrar")
        dispositivos.remove(p)
    }

}