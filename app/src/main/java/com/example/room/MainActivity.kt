package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import com.example.room.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first

class MainActivity : AppCompatActivity() {

    private lateinit var contactoDao: ContactoDao
    private lateinit var contactoAdapter: ContactoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Inicializar la base de datos
        val roomapp = applicationContext as RoomApp
        contactoDao = roomapp.getDatabase().contactoDao()
        // Inicializar el RecyclerView y el Adaptador
        contactoAdapter = ContactoAdapter()
        binding.recyclerViewContactos.adapter = contactoAdapter
        binding.recyclerViewContactos.layoutManager = LinearLayoutManager(this)
        // Iniciar la observación de la base de datos
        iniciarObservacionContactos()
        // Ejemplo de inserción de datos
        insertarEjemplo()
        binding.btnInsertar.setOnClickListener {
            insertarContacto()
        }
    }

    private fun iniciarObservacionContactos() {
        // Observa el Flow de contactos y actualiza el RecyclerView
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                contactoDao.obtenerTodos().collect { listaContactos ->
                    // Envía la nueva lista al adaptador
                    contactoAdapter.setContactos(listaContactos)
                    Log.d("AGENDA_DB", "RecyclerView actualizado con ${listaContactos.size} contactos (usando View Binding).")
                }
            }
        }
    }

    private fun insertarEjemplo() {
        lifecycleScope.launch {
            // Inserta contactos si la lista está vacía para probar
            if (contactoDao.obtenerTodos().first().isEmpty()) {
                contactoDao.insertar(Contacto(nombre = "Ana García", telefono = "555-4321", email = "ana@ejemplo.com"))
                contactoDao.insertar(Contacto(nombre = "Beto Díaz", telefono = "555-9876", email = "beto@ejemplo.com"))
                contactoDao.insertar(Contacto(nombre = "Carlos Soto", telefono = "555-0000", email = "carlos@ejemplo.com"))
            }
        }
    }

    private fun insertarContacto(){
        val nombre = binding.editTextText.text.toString()
        val telefono = binding.editTextPhone.text.toString()
        val email = binding.editTextTextEmailAddress.text.toString()
        lifecycleScope.launch {
            contactoDao.insertar(Contacto(nombre = nombre, telefono = telefono, email = email))
        }
    }
}