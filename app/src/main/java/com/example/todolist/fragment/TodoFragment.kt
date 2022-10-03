package com.example.todolist.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.databinding.DialogBoxBinding
import com.example.todolist.databinding.FragmentTodoBinding
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.model.Todo
import com.example.todolist.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.dialog_box.view.*

class TodoFragment : Fragment() {
    private lateinit var binding: FragmentTodoBinding
    private lateinit var bindingItem: ItemTodoBinding
    private lateinit var viewModel: TodoViewModel
    private lateinit var bindingDialog: DialogBoxBinding
    private var priorityLevel = ""

    private val todoAdapter: TodoAdapter = TodoAdapter(
        object : TodoAdapter.ItemClickListener {
            override fun onItemClick(position: Int) {
                // this function will handle the item click on a provided position
                deleteFromDatabase(position)
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoBinding.inflate(inflater, container, false)
        bindingItem = ItemTodoBinding.inflate(inflater, container, false)
        bindingDialog = DialogBoxBinding.inflate(inflater, container, false)

        //  recyclerview
        val adapter = todoAdapter
        val recyclerView = binding.rvTodo
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        //  view model
        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        viewModel.readAllData.observe(viewLifecycleOwner) { todo ->
            adapter.setData(todo)
        }

        binding.floatingActionButton.setOnClickListener {
//            Toast.makeText(context, "Floating Button clicked.", Toast.LENGTH_LONG).show()
            displayDialog()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    //  dialog box to get user input
    private fun displayDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_box, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setTitle("Add Todo")
        val dialogAlert = builder.show()
        dialogView.dialog_button_add.setOnClickListener {
            dialogAlert.dismiss()
            val inputTodo = dialogView.input_editText.text.toString()
            if (dialogView.urgent.isChecked) {
                priorityLevel = "Urgent"
            } else if (dialogView.high.isChecked) {
                priorityLevel = "High"
            } else if (dialogView.medium.isChecked) {
                priorityLevel = "Medium"
            } else {
                priorityLevel = "Low"
            }
//            Log.d("RadioGroup", "$inputTodo -> Priority level: $priorityLevel")
            insertToDatabase(inputTodo, priorityLevel)

        }
        dialogView.dialog_button_cancel.setOnClickListener {
            dialogAlert.dismiss()
        }
    }

    //  whatever input is typed in is inserted into a database and displayed
    private fun insertToDatabase(inputItem: String, priorityLevel: String) {
        val todo = Todo(0, inputItem, priorityLevel)
        viewModel.addTodo(todo)
    }

    private fun deleteFromDatabase(position: Int) {
        val todo = todoAdapter.getItemAt(position)
        viewModel.deleteTodo(todo)
    }
}