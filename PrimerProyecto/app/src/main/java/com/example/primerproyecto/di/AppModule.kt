package com.example.primerproyecto.di

import com.example.primerproyecto.domain.useCases.AddDispositivoUseCase
import com.example.primerproyecto.domain.useCases.CargarDispositivosUseCase
import com.example.primerproyecto.domain.useCases.DameMovilUseCase
import com.example.primerproyecto.domain.useCases.DelDispositivoUseCase
import com.example.primerproyecto.ui.main.MainViewModel

object AppModule {

    val dameMovilUseCase: DameMovilUseCase = DameMovilUseCase()
    val addDispositivoUseCase: AddDispositivoUseCase = AddDispositivoUseCase()
    val delDispositivoUseCase: DelDispositivoUseCase = DelDispositivoUseCase()
    val cargarDispositivosUseCase: CargarDispositivosUseCase = CargarDispositivosUseCase()


}