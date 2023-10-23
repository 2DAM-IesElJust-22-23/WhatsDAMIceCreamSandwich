package com.ieseljust.pmdm.whatsdamicecreamsandwich

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ieseljust.pmdm.whatsdamicecreamsandwich.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val editTextNickname = binding.nickNameText
        val editTextIPAddress = binding.serverAddressText
        val buttonConnect = binding.buttonConnect

        buttonConnect.setOnClickListener {
            val nickname = editTextNickname.text.toString()
            val ipAddress = editTextIPAddress.text.toString()

            if (nickname.isNotBlank() && isValidIPAddress(ipAddress)) {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Conexión establecida")
                alertDialog.setMessage("Nickname: $nickname\nDirección IP: $ipAddress")
                alertDialog.setPositiveButton("OK") { dialog, _ ->
                    val intent = Intent(this, MessagesWindow::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }
                alertDialog.show()
            } else {
                Toast.makeText(this, "El nickname no puede estar vacío y la dirección IP debe ser válida.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidIPAddress(ipAddress: String): Boolean {
        val ipAddressPattern = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$".toRegex()
        return ipAddressPattern.matches(ipAddress)
    }
}

