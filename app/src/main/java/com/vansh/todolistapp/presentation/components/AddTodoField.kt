package com.vansh.todolistapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.vansh.todolistapp.events.TodoEvents
import com.vansh.todolistapp.models.TodoState
import com.vansh.todolistapp.ui.theme.TodoListAppTheme
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTodoField(
    state: TodoState,
    events: (TodoEvents) -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        onDismissRequest = {
            events(TodoEvents.HideDialog)
        },
        confirmButton = {
            Button(onClick = { events(TodoEvents.SaveTodo) }) {
                Text(text = "SAVE")
            }
        },
        title = {
            Text(
                text = "Add A Todo",
                fontSize = 15.sp
            )
        },
        modifier = modifier,
        text = {
            OutlinedTextField(
                value = state.todo,
                onValueChange = {
                    events(TodoEvents.SetTodo(it))
                    events(TodoEvents.SetCreatedAt(LocalDate.now()))
                } ,
                placeholder = {
                    Text(text = "Enter the todo  ")
                },
                supportingText = {
                    Text(text = "* required")
                }
            )
        }
    )
}