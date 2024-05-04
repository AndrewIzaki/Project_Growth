package com.fatec.regrowth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fatec.regrowth.databinding.ActivityAddTaskMainBinding

class AddTask_main : AppCompatActivity() {

    private lateinit var binding : ActivityAddTaskMainBinding
    private lateinit var db: DATABASE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DATABASE(this)


    }
}