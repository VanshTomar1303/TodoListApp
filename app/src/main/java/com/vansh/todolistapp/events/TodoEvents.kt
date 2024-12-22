package com.vansh.todolistapp.events

import com.vansh.todolistapp.models.Todo
import java.time.LocalDate

sealed interface TodoEvents {
    data object SaveTodo: TodoEvents
    data class SetTodo(val todo: String): TodoEvents
    data class SetIsCompleted(val todo: Todo, val isCompleted: Boolean): TodoEvents
    data class SetCreatedAt(val createdAt: LocalDate): TodoEvents
    data object ShowDialog: TodoEvents
    data object HideDialog: TodoEvents
    data object ShowDetailDialog: TodoEvents
    data object HideDetailDialog: TodoEvents
    data class DeleteTodo(val todo: Todo): TodoEvents
}