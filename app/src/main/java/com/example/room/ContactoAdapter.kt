package com.example.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContactoAdapter : RecyclerView.Adapter<ContactoViewHolder>() {

    private var contactos = emptyList<Contacto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.list_item_contacto, parent, false)
        return ContactoViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        val currentContacto = contactos[position]
        holder.bind(currentContacto)
    }

    override fun getItemCount() = contactos.size

    fun setContactos(nuevaLista: List<Contacto>) {
        this.contactos = nuevaLista
        notifyDataSetChanged()
    }

}