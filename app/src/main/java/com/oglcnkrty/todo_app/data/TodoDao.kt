package com.oglcnkrty.todo_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.oglcnkrty.todo_app.model.TodoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("Select * from todos")
    fun getTodos() : Flow<List<TodoModel>>;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoModel: TodoModel)

    @Update
    suspend   fun updateTodo(todoModel: TodoModel)

    @Query("Select * from todos Where id = :id")
    suspend fun getTodoById(id : Int) : TodoModel?

    @Delete
   suspend fun deleteTodo(todoModel: TodoModel)

    @Query("Select * from todos Where title Like :query Or description Like :query ")
    fun searchTodo(query : String) : Flow<List<TodoModel>>



}