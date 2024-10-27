package com.jorgeatanacio.laboratoriocalificado02

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class activity_pedido : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)

        // Referencias a los campos de texto
        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val editTextNumero = findViewById<EditText>(R.id.editTextNumero)
        val editTextProductos = findViewById<EditText>(R.id.editTextProductos)
        val editTextDireccion = findViewById<EditText>(R.id.editTextDireccion)

        // Obtener los datos del intent
        val nombre = intent.getStringExtra("nombre")
        val numero = intent.getStringExtra("numero")
        val productos = intent.getStringExtra("productos")
        val direccion = intent.getStringExtra("direccion")

        // Asignar los valores a los EditText
        editTextNombre.setText(nombre)
        editTextNumero.setText(numero)
        editTextProductos.setText(productos)
        editTextDireccion.setText(direccion)

        // Llamar
        findViewById<ImageButton>(R.id.imbDial).setOnClickListener {
            val numeroTelefono = editTextNumero.text.toString()
            if (numeroTelefono.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$numeroTelefono")
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, ingresa un número", Toast.LENGTH_SHORT).show()
            }
        }

        // WhatsApp
        findViewById<ImageButton>(R.id.imbWsp).setOnClickListener {
            val nombreCliente = editTextNombre.text.toString()
            val listaProductos = editTextProductos.text.toString()
            val direccionEnvio = editTextDireccion.text.toString()

            if (nombreCliente.isNotEmpty() && listaProductos.isNotEmpty() && direccionEnvio.isNotEmpty()) {
                val mensaje = "Hola $nombreCliente, tus productos: $listaProductos están en camino a la dirección: $direccionEnvio"
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    setPackage("com.whatsapp")
                    putExtra(Intent.EXTRA_TEXT, mensaje)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, completa los campos antes de enviar", Toast.LENGTH_SHORT).show()
            }
        }

        // Google Maps
        findViewById<ImageButton>(R.id.imbMaps).setOnClickListener {
            val direccionEnvio = editTextDireccion.text.toString()
            if (direccionEnvio.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$direccionEnvio"))
                intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, ingresa una dirección", Toast.LENGTH_SHORT).show()
            }
        }
    }
}