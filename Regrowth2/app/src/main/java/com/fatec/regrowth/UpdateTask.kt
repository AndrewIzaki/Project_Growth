package com.fatec.regrowth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fatec.regrowth.databinding.ActivityUpdateTaskBinding

class UpdateTask : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: DATABASE
    private var noteId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DATABASE(this)

        noteId  = intent.getIntExtra("note_id", -1)
        if(noteId == -1){
            finish()
            return
        }

        val task = db.getTaskByID(noteId)
        binding.titleUpdtext.setText(task.title)
        binding.contenteUpdText.setText(task.content)

        binding.UpdBtn.setOnClickListener{
            val newTitle = binding.titleUpdtext.text.toString()
            val newContent = binding.contenteUpdText.text.toString()
            val updateNote = Task(noteId, newTitle, newContent)

            db.updateTask(updateNote)
            finish()
            Toast.makeText(this, "Mudança Salva", Toast.LENGTH_SHORT).show()
        }

    }

}