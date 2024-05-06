package com.fatec.regrowth

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*


class TaskAdapter(private var tasks: List<Task>, context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){
    class TaskViewHolder(itemView: View): ViewHolder(itemView){
        val titletextview: TextView = itemView.findViewById(R.id.titleEdittext)
        val contentTextView: TextView = itemView.findViewById(R.id.contenteditText)
        val updateBtn: ImageView = itemView.findViewById(R.id.UpdBtn)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.main_tasks, p0, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(p0: TaskViewHolder, p1: Int) {
        val task = tasks[p1]
        p0.titletextview.text = task.title
        p0.contentTextView.text = task.content
        p0.updateBtn.setOnClickListener{
            val intent = Intent(p0.itemView.context, UpdateTask::class.java).apply{
                putExtra("note_id", task.id)
            }
            p0.itemView.context.startActivity(intent)
        }
    }

    fun refreshData(newTasks: List<Task>){
        tasks = newTasks
        notifyDataSetChanged()
    }

}