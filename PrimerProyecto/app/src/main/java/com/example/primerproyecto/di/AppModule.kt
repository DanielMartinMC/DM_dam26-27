package com.example.primerproyecto.di

import com.example.primerproyecto.domain.useCases.DameMovilUseCase
import com.example.primerproyecto.ui.main.MainViewModel

object AppModule {

    fun provideMainViewModel(): MainViewModel = MainViewModel(dameMovilUseCase)

    val dameMovilUseCase: DameMovilUseCase = DameMovilUseCase()


}