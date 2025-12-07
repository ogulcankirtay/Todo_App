package com.oglcnkrty.todo_app.utilities


import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oglcnkrty.todo_app.base.listener.TodoItemClickListener
import com.oglcnkrty.todo_app.enums.Priority
import com.oglcnkrty.todo_app.model.TodoModel
import com.oglcnkrty.todo_app.ui.home.adapter.TodoAdapter

@BindingAdapter("setPriority")
fun setPriority(view: ImageView, priority: Priority?) {

    val context = view.context

    val color = when (priority) {
        Priority.HIGH -> android.R.color.holo_red_dark
        Priority.MEDIUM -> android.R.color.holo_orange_dark
        else -> android.R.color.holo_green_dark
    }

    ImageViewCompat.setImageTintList(
        view,
        ColorStateList.valueOf(ContextCompat.getColor(context, color))
    )
}

@BindingAdapter("todoList", "setOnClickListener", "searchQuery", "searchTodoList")
fun setAdapter(
    recyclerView: RecyclerView,
    todoList: List<TodoModel>?,
    clickListener: TodoItemClickListener,
    searchQuery: String,
    searchTodoList: List<TodoModel>?
) {

    recyclerView.apply {
        if (this.adapter == null) {
            adapter = TodoAdapter(clickListener).apply {
                submitList(
                    if (searchQuery.isEmpty()) {
                        todoList
                    } else {
                        searchTodoList
                    }
                )
            }

        } else {
            (adapter as TodoAdapter).submitList(
                if (searchQuery.isEmpty()) {
                    todoList
                } else {
                    searchTodoList
                }
            )
        }

    }

}