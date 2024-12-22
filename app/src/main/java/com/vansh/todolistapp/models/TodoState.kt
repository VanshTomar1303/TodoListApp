package com.vansh.todolistapp.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
data class TodoState(
    val todoList: List<Todo> = emptyList(),
    val todo: String = "",
    val createdAt: String = "",
    val isCompleted: Boolean = false,
    val isAddingTodo: Boolean = false,
    val showFullData: Boolean = false
)
