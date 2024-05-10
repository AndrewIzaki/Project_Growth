package com.fatec.regrowth

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter(private var tasks: List<Task>, context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    private val db: DATABASE = DATABASE(context)

    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titletextview: TextView = itemView.findViewById(R.id.tasksTitle)
        val contentTextView: TextView = itemView.findViewById(R.id.tasksContent)
        val updateBtn: ImageView = itemView.findViewById(R.id.update_button)
        val dltBtn: ImageView = itemView.findViewById(R.id.delete_button)
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
        p0.dltBtn.setOnClickListener{
            db.deletTask(task.id)
            refreshData(db.getallTasks())
            Toast.makeText(p0.itemView.context, "nota deletada", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newTasks: List<Task>){
        tasks = newTasks
        notifyDataSetChanged()
    }

}