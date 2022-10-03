package com.example.todolist.fragment

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.model.Todo
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    private var todoList = emptyList<Todo>()

    class TodoViewHolder(private val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.apply {
                todoItem.text = todo.todoItem
                when(todo.priorityLevel) {
                    "Urgent" -> todoItem.setTextColor(Color.argb(255,208, 49, 45))
                    "High" -> todoItem.setTextColor(Color.argb(255,104, 131, 188))
                    "Medium" -> todoItem.setTextColor(Color.argb(255, 138, 48, 127))
                    "Low" -> todoItem.setTextColor(Color.argb(255, 1, 135, 134))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentItem = todoList[position]
        holder.bind(currentItem)
        holder.itemView.todoCard.checkBox.isChecked = false
        holder.itemView.todoCard.checkBox.setOnClickListener {
            Log.d("Adapter", "Checked: ${currentItem.todoItem} with id: ${currentItem.id}")
            itemClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = todoList.size

    fun setData(todo: List<Todo>) {
        this.todoList = todo
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    fun getItemAt(position: Int): Todo = todoList[position]

}