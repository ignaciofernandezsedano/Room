package com.example.room

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ListItemContactoBinding

class ContactoViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    val binding: ListItemContactoBinding = ListItemContactoBinding.bind(v)

    fun bind(contacto: Contacto) {
        binding.tvNombre.text = contacto.nombre
        binding.tvTelefono.text = contacto.telefono
    }

}