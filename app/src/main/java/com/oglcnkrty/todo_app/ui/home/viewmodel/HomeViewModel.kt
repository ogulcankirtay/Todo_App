package com.oglcnkrty.todo_app.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.oglcnkrty.todo_app.data.Repository
import com.oglcnkrty.todo_app.model.TodoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {

    val todoList = repository.localDataSource.getTodos().asLiveData()

    fun updateTodo(todoModel: TodoModel) {
        val updatedTodo = todoModel.copy(isDone = todoModel.isDone?.not())
        viewModelScope.launch {
            repository.localDataSource.updateTodo(updatedTodo)
        }
    }


}