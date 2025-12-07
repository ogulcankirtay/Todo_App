package com.oglcnkrty.todo_app.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oglcnkrty.todo_app.base.listener.TodoItemClickListener
import com.oglcnkrty.todo_app.databinding.ItemTodoBinding
import com.oglcnkrty.todo_app.model.TodoModel

class TodoViewHolder(private val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(todoModel: TodoModel,clickListener: TodoItemClickListener){

        binding.toDoModel=todoModel
        binding.executePendingBindings()

     binding.todoClickListener=clickListener
    }

    companion object{
        fun from(parent: ViewGroup): TodoViewHolder{
            val binding=ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),parent,false)

            return TodoViewHolder(binding)

        }
    }

}
