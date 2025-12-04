package com.oglcnkrty.todo_app.base.listener

import com.oglcnkrty.todo_app.model.TodoModel

interface TodoItemClickListener {

    fun onTodoItemClickListener(id: Int);

    fun onTodoChecked(todoModel: TodoModel)
}