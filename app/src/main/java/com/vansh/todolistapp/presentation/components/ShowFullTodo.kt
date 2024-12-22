package com.vansh.todolistapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vansh.todolistapp.events.TodoEvents
import com.vansh.todolistapp.models.Todo

@Composable
fun ShowFullTodo(
    state: Todo,
    events: (TodoEvents) -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        onDismissRequest = { events(TodoEvents.HideDetailDialog) },
        confirmButton = {
            IconButton(onClick = {
                events(TodoEvents.HideDetailDialog)
            })
            {
            Icon(imageVector = Icons.Default.Close, contentDescription ="close" )
        } },
        title = {
            Text(
                text = "Todo Details",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Todo ID : ${state.id}",
                    fontSize = 17.sp
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = state.todoTask,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Created At : ${state.createdAt}",
                    fontSize = 19.sp
                )
            }
        }
    )
}
