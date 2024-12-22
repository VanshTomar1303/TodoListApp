package com.vansh.todolistapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vansh.todolistapp.events.TodoEvents
import com.vansh.todolistapp.models.Todo
import com.vansh.todolistapp.models.TodoState

@Composable
fun TodoField(
    state: TodoState,
    events: (TodoEvents) -> Unit,
    todo: Todo,
    modifier: Modifier = Modifier
){
    val isTaskDone = todo.isCompleted
    Box(
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .border(1.dp, Color.Blue, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = modifier
                .align(Alignment.TopStart)
                .padding(10.dp)
        ) {
            Text(
                text = todo.id.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = if(todo.todoTask.length >= 15){
                    todo.todoTask.substring(0,15)+"..."
                }else{
                    todo.todoTask
                },
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = modifier
                    .padding(start = 30.dp),
                textDecoration = if(isTaskDone){
                    TextDecoration.LineThrough
                }else{
                    TextDecoration.None
                }
            )
            Text(
                text = todo.createdAt,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = modifier
                    .padding(top = 15.dp)
            )
        }
        Column(
            modifier = modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
        ) {
                Text(
                    text = if(isTaskDone) "Task Done" else "Task Incomplete",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = modifier.padding(horizontal = 20.dp)
                )
            Spacer(modifier = modifier.height(16.dp))
            Row(
                modifier = modifier
                    .padding(horizontal = 15.dp)
            ) {
                IconButton(
                    onClick = {
                        events(TodoEvents.SetIsCompleted(todo,!todo.isCompleted))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Task Done"
                    )
                }
                IconButton(
                    onClick = {
                        events(TodoEvents.ShowDetailDialog)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Details"
                    )
                }
                IconButton(
                    onClick = {events(TodoEvents.DeleteTodo(todo))}
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
            if(state.showFullData){
                ShowFullTodo(state = todo,events = events,)
            }

        }
    }
}
