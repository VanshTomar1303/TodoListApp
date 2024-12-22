package com.vansh.todolistapp.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vansh.todolistapp.dao.TodoListDao
import com.vansh.todolistapp.events.TodoEvents
import com.vansh.todolistapp.models.Todo
import com.vansh.todolistapp.models.TodoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class TodoListViewModel(
    private val todoListDao: TodoListDao
) : ViewModel() {
    private val _state = MutableStateFlow(TodoState())
    private val _todo = todoListDao.getAllTodo()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val state = combine(_state,_todo){ state,todo ->
        state.copy(
            todoList = todo
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TodoState())

    fun onEvent(event: TodoEvents) {
        when (event) {
            is TodoEvents.DeleteTodo -> {
                viewModelScope.launch {
                    todoListDao.deleteTodo(event.todo)
                }
            }

            TodoEvents.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingTodo = false
                    )
                }
            }

            TodoEvents.HideDetailDialog -> {
                _state.update {
                    it.copy(
                        showFullData = false
                    )
                }
            }

            TodoEvents.SaveTodo -> {
                val todo = state.value.todo
                val createdAt = state.value.createdAt
                val isCompleted = state.value.isCompleted

                if (todo.isBlank()) return

                val todoTask =
                    Todo(
                        todoTask = todo,
                        createdAt = createdAt,
                        isCompleted = isCompleted,
                    )


                viewModelScope.launch {
                        todoListDao.addTodo(todoTask)
                }

                _state.update {
                    it.copy(
                        isAddingTodo = false,
                        todo = "",
                        isCompleted = false,
                        createdAt = ""
                    )
                }
            }

            is TodoEvents.SetTodo -> {
                _state.update {
                    it.copy(
                        todo = event.todo
                    )
                }
            }

            is TodoEvents.SetIsCompleted -> {
                viewModelScope.launch {
                    // Update the database and then refresh state
                    todoListDao.addTodo(event.todo.copy(isCompleted = event.isCompleted))
                    // The combined flow will update automatically
                }
            }


            is TodoEvents.SetCreatedAt -> {
                _state.update {
                    it.copy(
                        createdAt = event.createdAt.toString()
                    )
                }
            }

            TodoEvents.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingTodo = true
                    )
                }
            }
            TodoEvents.ShowDetailDialog -> {
                _state.update {
                    it.copy(
                        showFullData = true
                    )
                }
            }

        }
    }
}