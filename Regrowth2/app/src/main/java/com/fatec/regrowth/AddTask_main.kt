package com.fatec.regrowth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fatec.regrowth.databinding.ActivityAddTaskMainBinding

class AddTask_main : AppCompatActivity() {

    private lateinit var binding : ActivityAddTaskMainBinding
    private lateinit var db: DATABASE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DATABASE(this)

        binding.SaveBtn.setOnClickListener{
            val title = binding.titleEdittext.text.toString()
                val content = binding.contenteditText.text.toString()
                val Task = Task(0, title, content)
            db.insertTask(Task)
            finish()
            Toast.makeText(this,"texto adicionado", Toast.LENGTH_SHORT).show()
        }
    }
}