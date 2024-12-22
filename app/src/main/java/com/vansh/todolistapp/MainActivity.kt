package com.vansh.todolistapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vansh.todolistapp.database.TodoListDatabase
import com.vansh.todolistapp.ui.theme.TodoListAppTheme
import androidx.room.Room
import com.vansh.todolistapp.presentation.TodoListField
import com.vansh.todolistapp.viewmodels.TodoListViewModel

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {

    // database
    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TodoListDatabase::class.java,
            "todolist_db"
        ).build()
    }

    // viewmodel
    private val viewModel by viewModels<TodoListViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TodoListViewModel(db.todoListDao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListAppTheme {
                val state by viewModel.state.collectAsState()
                TodoListField(state = state, event = viewModel::onEvent)
            }
        }
    }
}
