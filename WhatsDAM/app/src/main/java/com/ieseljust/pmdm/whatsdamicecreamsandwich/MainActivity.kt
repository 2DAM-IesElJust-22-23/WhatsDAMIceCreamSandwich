package com.example.whatsdamicecreamsandwich

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
//import  com.example.whatsdamicecreamsandwich.activity_messages_window

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editTextNickname = findViewById<EditText>(R.id.nickNameText)
        val editTextIPAddress = findViewById<EditText>(R.id.serverAddressText)
        val buttonConnect = findViewById<Button>(R.id.buttonConnect)

        buttonConnect.setOnClickListener {
            val nickname = editTextNickname.text.toString()
            val ipAddress = editTextIPAddress.text.toString()

            if (nickname.isNotBlank() && isValidIPAddress(ipAddress)) {
                // Simula la connexió mitjançant un AlertDialog
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Connexió establerta")
                alertDialog.setMessage("NickName: $nickname\nAdreça IP: $ipAddress")
                alertDialog.setPositiveButton("OK") { dialog, _ ->
                    // Iniciar la segunda actividad
                    val intent = Intent(this, activity_messages_window::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }
                alertDialog.show()
            } else {
                Toast.makeText(this, "Nickname no pot estar buit i l'adreça IP ha de ser vàlida.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidIPAddress(ipAddress: String): Boolean {
        val ipAddressPattern = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$".toRegex()
        return ipAddressPattern.matches(ipAddress)
    }
}
