package com.oglcnkrty.todo_app.ui.Edit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oglcnkrty.todo_app.data.Repository
import com.oglcnkrty.todo_app.model.TodoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewAndEditViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {

    val todo = MutableLiveData<TodoModel?>()

    fun insertTodo(todoModel: TodoModel) {

        viewModelScope.launch {
            repository.localDataSource.insertTodo(todoModel)
        }
    }

    fun updateTodo(todoModel: TodoModel) {
        todoModel.id = todo.value?.id ?: 0
        viewModelScope.launch {
            repository.localDataSource.updateTodo(todoModel)
        }
    }

    fun deleteTodo() {

        viewModelScope.launch {
            todo.value?.let {
                repository.localDataSource.deleteTodo(it)
            }
        }
    }

    fun getTodoById(id: Int) {

        viewModelScope.launch {
            val todoModel = repository.localDataSource.getTodoById(id)
            todo.value = todoModel
        }
    }
}