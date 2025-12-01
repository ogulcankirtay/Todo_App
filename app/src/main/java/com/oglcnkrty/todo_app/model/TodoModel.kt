package com.oglcnkrty.todo_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.oglcnkrty.todo_app.enums.Priority;

@Entity(tableName = "todos")
data class TodoModel(
        @PrimaryKey(autoGenerate = true)  val id : Int=0,
        val title : String?,
        val description : String?,
        val priority :Priority,
        val date : String?,
        val isDone : Boolean?
        )

