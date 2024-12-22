package com.vansh.todolistapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vansh.todolistapp.dao.TodoListDao
import com.vansh.todolistapp.models.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoListDatabase : RoomDatabase() {
    abstract val todoListDao: TodoListDao
}