package com.example.primerproyecto.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
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
            AppModule.addDispositivoUseCase,
            AppModule.delDispositivoUseCase,
        )
    }

    private lateinit var adapter: DispositivoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.handleCargaLista()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configRecycler()
        observarEstado()
        setupEventos()
    }

    private fun configRecycler() {
        binding.listado.layoutManager = LinearLayoutManager(this)

        adapter = DispositivoAdapter { dispositivo ->
            viewModel.handleDelete(dispositivo)
        }
        binding.listado.adapter = adapter
    }

    private fun setupEventos() {
        with(binding) {
            button.setOnClickListener {
                viewModel.handleDameMovil()
                viewModel.handleCargaLista()
            }

            buttonAdd.setOnClickListener {
                val p = Dispositivo(marca = "Apple", modelo = "iphone 18")
                viewModel.handleAdd(p)
            }
        }
    }

    private fun observarEstado() {
        viewModel.state.observe(this) { state ->
            state?.movil?.let {
                binding.textView.text = it
            }

            state?.dispositivos?.let {
                adapter.submitList(it)
            }

            state?.error?.let { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                viewModel.handleLimpiarError()
            }
        }
    }
}
