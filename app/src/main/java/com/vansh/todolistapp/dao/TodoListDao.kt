package com.vansh.todolistapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.vansh.todolistapp.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoListDao {

    @Query("Select * from Todo")
    fun getAllTodo(): Flow<List<Todo>>

    @Upsert
    suspend fun addTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

//    @Query("select * from Todo where id = :id ")
//    fun findTodoById(id: Int): Todo
}