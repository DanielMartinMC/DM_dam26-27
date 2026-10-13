package com.example.primerproyecto.ui.main

import android.R.attr.button
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primerproyecto.R
import com.example.primerproyecto.databinding.ActivityMainBinding
import com.example.primerproyecto.di.AppModule
import com.example.primerproyecto.domain.model.Dispositivo

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            AppModule.dameMovilUseCase,
            AppModule.cargarDispositivosUseCase,
            AppModule.delDispositivoUseCase,
            AppModule.addDispositivoUseCase,

            )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.handleCargarLista()

        setContentView(R.layout.binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configRecycler()

        observarEstado()

        setupEventos()


    }

    private fun setupEventos() {
        with(binding) {
            button.setOnClickListener {
                // no se cambian interfaz fuera del observable
                //binding.textView.text = viewModel.damePresidente()

                viewModel.handleDameMovil()
                viewModel.handleCargaLista()
            }

            buttonAdd.setOnClickListener {
                var p = Dispositivo(marca = "Apple", modelo = "Iphone 18 ")

                viewModel.handleAdd(p)
            }
        }
    }

    private fun observarEstado() {
        viewModel.state.observe(this,{
            it?.dispositivo.let{
                binding.textView.text = it
            }

            it?.error?.let{ error ->
                Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
            }
        })


    }

}