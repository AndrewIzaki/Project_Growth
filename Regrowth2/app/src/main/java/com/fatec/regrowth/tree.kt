package com.fatec.regrowth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class tree : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tree)
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