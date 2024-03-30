package com.fatec.regrowth

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class cronometro : AppCompatActivity() {


    private var pause = false
    private var teste: CountDownTimer? = null // a interrogação permite que o valor seja nulo
    private lateinit var  textInputEditText: TextInputEditText
    private lateinit var  textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cronometro)

        textInputEditText = findViewById(R.id.countdown_min)
        textView = findViewById(R.id.txt_teste)

        val reset = findViewById<Button>(R.id.Btn_Reset)
        reset.setOnClickListener{
        }

        val startCron = findViewById<ImageButton>(R.id.Btn_cronometro)
        startCron.setOnClickListener{
            startCount()
        }
    }

// todos os metodos do script, Fica ligado Jerjinho matador.
fun startCount() {
    val tempoString = textInputEditText.text.toString()
    val tempoLong = tempoString.toLongOrNull() ?: 0 // Definindo o tempo padrão como 0 se não for possível converter para Long

    if (tempoLong > 0) {
        if (teste == null) {// fun start
            teste = object : CountDownTimer(tempoLong * 60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val segundosRestantes = millisUntilFinished / 1000
                    val minutos = segundosRestantes / 60
                    val segundos = segundosRestantes % 60
                    textView.text = String.format("%02d:%02d", minutos, segundos) // Formatação para exibir minutos e segundos no formato "00:00"
                }

                override fun onFinish() {
                    textView.text = "00:00"
                    teste = null // Limpa a referência do cronômetro quando a contagem regressiva termina
                }
            }
            teste?.start()// fun start
            Toast.makeText(this, "iniciado", Toast.LENGTH_SHORT).show()

        } else{
            teste?.cancel()
            if(pause){ //reiniciando o cornometro
                teste?.start()
                Toast.makeText(this, "reiniciado", Toast.LENGTH_SHORT).show()
                pause = false
                return
            }

            Toast.makeText(this, "pausado", Toast.LENGTH_SHORT).show()
            pause = true
//            teste = null  Limpa a referência do cronômetro quando a contagem regressiva é cancelada
//            textView.text = "00:00"
        }
    } else {
        Toast.makeText(this, "Por favor, insira um tempo válido", Toast.LENGTH_SHORT).show()
    }
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