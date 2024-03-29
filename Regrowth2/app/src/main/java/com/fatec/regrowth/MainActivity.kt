package com.fatec.regrowth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import android.app.AlertDialog
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var layoutTasks: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutTasks = findViewById(R.id.linearLayout_Tasks)


        findViewById<Button>(R.id.plus).setOnClickListener {
            adicionar()
        }
    }

    fun adicionar() {

        val includedLayout = layoutInflater.inflate(R.layout.task, null)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 8, 0, 0)
        includedLayout.layoutParams = layoutParams

        layoutTasks.addView(includedLayout)
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