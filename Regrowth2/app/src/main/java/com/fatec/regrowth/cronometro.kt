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

    // Variaveis globais
    private var pauseCount: Long = 0
    private var valueTest: Long = 0
    private var pause = false
    private var TimIniciar: CountDownTimer? = null
    private var redo: CountDownTimer? = null
    var tempoLong: Long = 0
    private lateinit var  textInputEditText: TextInputEditText
    private lateinit var  textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cronometro)

        textInputEditText = findViewById(R.id.countdown_min)
        textView = findViewById(R.id.txt_teste)

        val tempoString = textInputEditText.text.toString()
        tempoLong = tempoString.toLongOrNull() ?: 0 // Definindo o tempo padrão como 0 se não for possível converter para Long

        val reset = findViewById<Button>(R.id.Btn_Reset)
        reset.setOnClickListener{
        reset()
    }

        val startCron = findViewById<ImageButton>(R.id.Btn_cronometro)
        startCron.setOnClickListener{
            startCount(tempoLong)
        }

        findViewById<Button>(R.id.Btn_Pause).setOnClickListener{
            if (pause) {
                ConCount()
                pause = false
            }else {
                ConCount()
                pause = true
            }
        }
    }

// todos os metodos do script, Fica ligado Jerjinho matador.
fun startCount(tempoLong: Long) {
    if (tempoLong > 0){//1°
        if (TimIniciar == null){//2° fun start
            if(pause == false){//3°
                val tempoString = textInputEditText.text.toString()
                val tempoLong = tempoString.toLongOrNull() ?: 0
// Definindo o tempo padrão como 0 se não for possível converter para Long

            TimIniciar = object : CountDownTimer(tempoLong * 60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                    val segundosRestantes = millisUntilFinished / 1000
                    val minutos = segundosRestantes / 60
                    val segundos = segundosRestantes % 60
                    textView.text = String.format("%02d:%02d", minutos, segundos) // Formatação para exibir minutos e segundos no formato "00:00"
                    pauseCount = millisUntilFinished
                }

                override fun onFinish() {
                    textView.text = "00:00"
                    TimIniciar = null // Limpa a referência do cronômetro quando a contagem regressiva termina
                }
            }
                pause = true
            TimIniciar?.start()// fun start
                Toast.makeText(this, "iniciado", Toast.LENGTH_SHORT).show()
            }
        }//2° final
    } else {//1° - else
        Toast.makeText(this, "Por favor, insira um tempo válido", Toast.LENGTH_SHORT).show()
    }
}// 1° final
private fun reset(){
    textView.text = "00:00"
    redo?.cancel()
    TimIniciar?.cancel()
    redo = null
    TimIniciar = null
}
private fun teste(tempoLong: Long){
    if(redo == null && TimIniciar != null){
    redo = object : CountDownTimer(tempoLong, 1000) {
        override fun onTick(millisUntilFinished: Long) {

            val segundosRestantes = millisUntilFinished / 1000
            val minutos = segundosRestantes / 60
            val segundos = segundosRestantes % 60
            textView.text = String.format("%02d:%02d", minutos, segundos) // Formatação para exibir minutos e segundos no formato "00:00"
            pauseCount = millisUntilFinished
        }
        override fun onFinish(){
            textView.text = "00:00"
            redo = null // Limpa a referência do cronômetro quando a contagem regressiva termina
        }
    }
    pause = true
    redo?.start()// fun start
    }
}
private fun ConCount(){
            if (pause) {//3°
                redo?.cancel()
                TimIniciar?.cancel()
                valueTest = pauseCount
                Toast.makeText(this, "Pausado", Toast.LENGTH_SHORT).show()
            }
            else{

                redo = null
                teste(valueTest)
                Toast.makeText(this, valueTest.toString(), Toast.LENGTH_SHORT).show()
            }
    }

        //voltar
        fun cronometro(view: View) {
            val intent = Intent(this, cronometro::class.java)
            startActivity(intent)
        }

        fun tree(view: View) {
            val intent = Intent(this, tree::class.java)
            startActivity(intent)
        }

        fun main(view: View) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
