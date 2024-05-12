package com.fatec.regrowth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import org.json.JSONArray
import org.json.JSONObject
import DraggableView
import android.util.Log
import android.view.MotionEvent

class tree : AppCompatActivity() {

    private lateinit var layout: ConstraintLayout
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var DV: DraggableView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tree)

        layout = findViewById(R.id.layout)
        sharedPreferences = getSharedPreferences("drag_objects", Context.MODE_PRIVATE)

        val btnAddLeaf = findViewById<Button>(R.id.btnFolha)
        btnAddLeaf.setOnClickListener {
            val newLeaf = addLeaf(layout, 100, 100, 100, 100)
            saveLeaf(newLeaf)
            Toast.makeText(this, "Leaf added", Toast.LENGTH_SHORT).show()
        }

        var valor = intent.getBooleanExtra("leaf", false)
        if (valor) {
            val newLeaf = addLeaf(layout, 100, 100, 100, 100)
            saveLeaf(newLeaf)
            Toast.makeText(this, "Leaf added", Toast.LENGTH_SHORT).show()


            val intent = Intent(this@tree, Cronometro::class.java)
            intent.putExtra("leaf", false)
        }

        retrieveLeaves()
    }


    private fun addLeaf(layout: ConstraintLayout, width: Int, height: Int, left: Int, top: Int): DraggableView {
        val newLeaf = DraggableView(this)
        val layoutParams = ConstraintLayout.LayoutParams(width, height)
        layoutParams.topMargin = top
        layoutParams.leftMargin = left
        newLeaf.layoutParams = layoutParams
        newLeaf.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                saveLeaf(newLeaf)
            }
            false
        }
        layout.addView(newLeaf)
        return newLeaf
    }

    private fun saveLeaf(leaf: DraggableView) {
        val jsonArray = JSONArray()
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (child is DraggableView) {
                val jsonObject = JSONObject()
                val location = IntArray(2)
                child.getLocationOnScreen(location)
                jsonObject.put("left", location[0])
                jsonObject.put("top", location[1])
                jsonArray.put(jsonObject)
            }
        }
        Log.d("JSONArray", jsonArray.toString())
        val editor = sharedPreferences.edit()
        editor.putString("drag_objects", jsonArray.toString())
        editor.apply()
    }

    private fun retrieveLeaves() {
        val jsonArrayString = sharedPreferences.getString("drag_objects", null)
        if (jsonArrayString != null) {
            val jsonArray = JSONArray(jsonArrayString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val left = jsonObject.getInt("left")
                val top = jsonObject.getInt("top")
                val newLeaf = addLeaf(layout, 100, 100, left, top)
                moveImageViewTo(newLeaf, left, top)
            }
        }
    }

    private fun moveImageViewTo(view: DraggableView, x: Int, y: Int) {
        view.x = x.toFloat()
        view.y = y.toFloat()
        layout.requestLayout()
    }
    fun Cronometro(view: View) {
        val intent = Intent(this, Cronometro::class.java)
        startActivity(intent)
    }

    fun main(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}