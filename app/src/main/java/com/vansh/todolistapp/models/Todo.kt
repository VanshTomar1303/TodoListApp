package com.vansh.todolistapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var todoTask: String,
    var createdAt: String,
    var isCompleted: Boolean = false
)
