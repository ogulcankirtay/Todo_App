package com.oglcnkrty.todo_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oglcnkrty.todo_app.model.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class ToddDatabase : RoomDatabase(){
    abstract fun todoDao(): TodoDao

}