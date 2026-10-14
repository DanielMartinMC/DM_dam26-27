package com.example.primerproyecto.ui.main

import com.example.primerproyecto.domain.model.Dispositivo

data class MainState(
    val movil: String,
    val dispositivos: List<Dispositivo>,
    val error: String? = null,


)


