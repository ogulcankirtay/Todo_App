package com.oglcnkrty.todo_app.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oglcnkrty.todo_app.base.listener.TodoItemClickListener
import com.oglcnkrty.todo_app.model.TodoModel

class TodoAdapter(private val listener: TodoItemClickListener): ListAdapter<TodoModel,TodoViewHolder>(TodoDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoViewHolder = TodoViewHolder.from(parent)

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoModel = currentList[position],listener)
    }

    private class TodoDiffUtil(): DiffUtil.ItemCallback<TodoModel>() {
        override fun areItemsTheSame(
            oldItem: TodoModel,
            newItem: TodoModel
        ): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: TodoModel,
            newItem: TodoModel
        ): Boolean {
            return newItem.areContentsTheSame(oldItem)
        }
    }
}