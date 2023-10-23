package com.ieseljust.pmdm.whatsdamicecreamsandwich

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ieseljust.pmdm.whatsdamicecreamsandwich.databinding.ActivityMessagesWindowBinding

class MessagesWindow : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesWindowBinding
    private lateinit var adapter: MensajeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MensajeAdapter(Mensajes.listaMensajes)
        recyclerView.adapter = adapter

        val messageEditText = binding.MessageText
        val sendButton = binding.sendMessage

        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString().trim()
            if (messageText.isNotEmpty()) {
                // Agregar el mensaje a la lista de mensajes
                Mensajes.listaMensajes.add(Mensaje("Usuario", messageText))
                val position = Mensajes.listaMensajes.size - 1
                adapter.notifyItemInserted(position)
                // Hacer scroll hasta el último mensaje
                recyclerView.smoothScrollToPosition(position)
                // Limpiar el campo de texto
                messageEditText.text.clear()
            }
        }
    }
}

class MensajeAdapter(private val mensajes: List<Mensaje>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vista = inflater.inflate(R.layout.my_msg_viewholder, parent, false)
        return MyViewHolder(vista)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mensajes[position])
    }

    override fun getItemCount(): Int {
        return mensajes.size
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mensaje = itemView.findViewById(R.id.msg_text) as TextView

    fun bind(msg: Mensaje) {
        mensaje.text = msg.textoMensaje
    }
}



















