package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contacto::class], version = 1, exportSchema = false)
abstract class AgendaDatabase : RoomDatabase() {

    // Room generará la implementación de este metodo
    abstract fun contactoDao(): ContactoDao
}