package com.fatec.regrowth
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.fatec.regrowth.R
import org.json.JSONArray
import org.json.JSONObject

class tree : AppCompatActivity() {

    private lateinit var layout: ConstraintLayout
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tree)

        layout = findViewById(R.id.layout)
        val btn = findViewById<Button>(R.id.btnFolha)
        sharedPreferences = getSharedPreferences("drag_objects", Context.MODE_PRIVATE)


        val valor = intent.getBooleanExtra("leaf", false)
        if(valor){
            val novoObjeto = adicionarObjeto(layout, 100, 100)
            salvarObjeto(novoObjeto)
            return
        }
        // Recupere os objetos ao iniciar o aplicativo
        recuperarObjetos()
    }

    private fun adicionarObjeto(layout: ConstraintLayout, width: Int, height: Int): DraggableView {
        val novoObjeto = DraggableView(this)
        val layoutParams = ConstraintLayout.LayoutParams(width, height)

        layoutParams.topMargin = 10 // Margem superior
        layoutParams.leftMargin = 10 // Margem esquerda
        novoObjeto.layoutParams = layoutParams
        layout.addView(novoObjeto)
        return novoObjeto
    }

    private fun salvarObjeto(objeto: DraggableView) {
        val jsonArray = JSONArray()
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (child is DraggableView) {
                val jsonObject = JSONObject()
                jsonObject.put("left", child.left)
                jsonObject.put("top", child.top)
                jsonArray.put(jsonObject)
            }
        }
        val editor = sharedPreferences.edit()
        editor.putString("drag_objects", jsonArray.toString())
        editor.apply()
    }

    private fun recuperarObjetos() {
        val jsonArrayString = sharedPreferences.getString("drag_objects", null)
        if (jsonArrayString != null) {
            val jsonArray = JSONArray(jsonArrayString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val left = jsonObject.getInt("left")
                val top = jsonObject.getInt("top")
                adicionarObjeto(layout, 100, 100).apply {
                    this.translationX = left.toFloat()
                    this.translationY = top.toFloat()
                }
            }
        }
        }
        fun cronometro(view: View){
            val intent = Intent(this, Cronometro::class.java)
            startActivity(intent)
        }

        fun main(view: View){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

}
