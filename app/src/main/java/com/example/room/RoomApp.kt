package com.example.room

import android.app.Application
import androidx.room.Room

class RoomApp: Application() {

    // Marcamos como volatile para asegurar que el valor es siempre el mismo
    @Volatile
    private var INSTANCE: AgendaDatabase? = null

    fun getDatabase(): AgendaDatabase {
        // Si la instancia ya existe, la devolvemos
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                applicationContext,
                AgendaDatabase::class.java,
                "agenda_database" // Nombre del archivo de la DB
            ).build()
            INSTANCE = instance
            // Devolvemos la instancia
            instance
        }
    }
}