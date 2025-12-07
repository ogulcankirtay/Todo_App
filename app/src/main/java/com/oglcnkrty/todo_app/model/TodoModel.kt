package com.oglcnkrty.todo_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.oglcnkrty.todo_app.enums.Priority;

@Entity(tableName = "todos")
data class TodoModel(
    @PrimaryKey(autoGenerate = true) var id : Int=0,
    val title : String?,
    val description : String?,
    val priority :Priority,
    var isDone : Boolean?
        ){

        fun areContentsTheSame(newModel: TodoModel): Boolean{

                return this.isDone==newModel.isDone && this.title==newModel.title
                        && this.description==newModel.description && this.priority==newModel.priority

        }
}

