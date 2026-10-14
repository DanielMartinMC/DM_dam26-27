package com.example.primerproyecto.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.databinding.ItemDispositivoBinding
import com.example.primerproyecto.domain.model.Dispositivo

class DispositivoAdapter(
    val delDispositivo: (Dispositivo) -> Unit,
) : ListAdapter<Dispositivo, DispositivoAdapter.DispositivoViewHolder>(DispositivoDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DispositivoViewHolder {
        val binding = ItemDispositivoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DispositivoViewHolder(binding, delDispositivo)
    }

    override fun onBindViewHolder(
        holder: DispositivoViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class DispositivoViewHolder(
        private val binding: ItemDispositivoBinding,
        private val onClickDelete: (Dispositivo) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(p: Dispositivo) {
            with(binding) {
                tvMarcaMovil.text = p.marca

                root.setOnClickListener {
                    onClickDelete(p)
                }
            }
        }
    }
}

class DispositivoDiffCallback : DiffUtil.ItemCallback<Dispositivo>() {
    override fun areItemsTheSame(oldItem: Dispositivo, newItem: Dispositivo): Boolean =
        oldItem.marca == newItem.marca

    override fun areContentsTheSame(oldItem: Dispositivo, newItem: Dispositivo): Boolean =
        oldItem == newItem
}
