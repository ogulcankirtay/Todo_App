package com.oglcnkrty.todo_app.data

import com.oglcnkrty.todo_app.model.TodoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject
constructor(
    private val todoDao: TodoDao
) {

    fun getTodos() : Flow<List<TodoModel>>{

        return todoDao.getTodos();
    }

    suspend fun insertTodo(todoModel: TodoModel)
    {
         todoDao.insertTodo(todoModel);
    }

    suspend   fun updateTodo(todoModel: TodoModel)
    {
        todoDao.updateTodo(todoModel);
    }

    suspend fun getTodoById(id : Int) : TodoModel?
    {
        return todoDao.getTodoById(id);
    }

    suspend fun deleteTodo(todoModel: TodoModel)
    {
        todoDao.deleteTodo( todoModel);
    }

    fun searchTodo(query : String) : Flow<List<TodoModel>>
    {
        return todoDao.searchTodo(query);
    }
}