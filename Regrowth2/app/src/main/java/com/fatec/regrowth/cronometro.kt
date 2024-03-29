package com.fatec.regrowth

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class cronometro : AppCompatActivity() {

    private lateinit var  textInputEditText: TextInputEditText
    private lateinit var  textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cronometro)

        textInputEditText = findViewById(R.id.countdown_min)
        textView = findViewById(R.id.txt_teste)

        val startCron = findViewById<ImageButton>(R.id.Btn_cronometro)
        startCron.setOnClickListener{
            startCount()
        }
    }

    fun startCount(){
        val tempoString = textInputEditText.text.toString()
        val tempoLong = tempoString.toLongOrNull() ?: 0 // Definindo o tempo padrão como 0 se não for possível converter para Long


        object : CountDownTimer(tempoLong * 60000, 1000) { // Convertendo minutos para milissegundos (1 minuto = 60.000 milissegundos)
            override fun onTick(millisUntilFinished: Long) {
                val segundosRestantes = millisUntilFinished / 1000
                val minutos = segundosRestantes / 60
                val segundos = segundosRestantes % 60
                textView.text = String.format("%02d:%02d", minutos, segundos) // Formatação para exibir minutos e segundos no formato "00:00"
            }

            override fun onFinish() {
                textView.text = "00:00"
            }
        }.start()

    }
    fun cronometro(view: View){
        val intent = Intent(this, cronometro::class.java)
        startActivity(intent)
    }
    fun tree(view: View){
        val intent = Intent(this, tree::class.java)
        startActivity(intent)
    }

    fun main(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}