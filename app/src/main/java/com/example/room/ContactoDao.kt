package com.example.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(contacto: Contacto)

    @Update
    suspend fun actualizar(contacto: Contacto)

    @Delete
    suspend fun eliminar(contacto: Contacto)

    @Query("SELECT * FROM tabla_contactos ORDER BY nombre ASC")
    fun obtenerTodos(): Flow<List<Contacto>> // Flow no necesita 'suspend'

    @Query("SELECT * FROM tabla_contactos WHERE id = :idContacto")
    suspend fun obtenerPorId(idContacto: Int): Contacto?
}