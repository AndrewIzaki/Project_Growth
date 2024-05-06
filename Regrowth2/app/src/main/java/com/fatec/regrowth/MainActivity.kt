package com.fatec.regrowth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatec.regrowth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var db: DATABASE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DATABASE(this)
        taskAdapter = TaskAdapter(db.getallTasks(), this)

        binding.RecycleView.layoutManager = LinearLayoutManager(this)
        binding.RecycleView.adapter = taskAdapter

        binding.Addbtn.setOnClickListener{
            val intent = Intent(this, AddTask_main::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        taskAdapter.refreshData(db.getallTasks())
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