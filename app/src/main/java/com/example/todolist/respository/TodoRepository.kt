package com.example.todolist.respository

import androidx.lifecycle.LiveData
import com.example.todolist.data.TodoDao
import com.example.todolist.model.Todo

class TodoRepository(private val todoDao: TodoDao) {
    val readAllData: LiveData<List<Todo>> = todoDao.readAll()

    suspend fun addTodo(todo: Todo) {
        todoDao.addTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo){
        todoDao.deleteTodo(todo)
    }
}