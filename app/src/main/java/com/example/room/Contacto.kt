package com.example.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_contactos")
data class Contacto(
    // La clave primaria es auto-generada
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val telefono: String,
    val email: String? // El email puede ser opcional
)
