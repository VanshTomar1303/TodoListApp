package com.vansh.todolistapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vansh.todolistapp.events.TodoEvents
import com.vansh.todolistapp.models.Todo
import com.vansh.todolistapp.models.TodoState
import com.vansh.todolistapp.presentation.components.AddTodoField
import com.vansh.todolistapp.presentation.components.TodoField
import com.vansh.todolistapp.ui.theme.TodoListAppTheme
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListField(
    state: TodoState,
    event: (TodoEvents) -> Unit,
    modifier: Modifier = Modifier
){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Todo List App",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarColors(
                    Color.Cyan,
                    Color.Green,
                    Color.LightGray,
                    Color.Magenta,
                    Color.Blue
                ),
                actions = {
                    IconButton(onClick = {
                        event(TodoEvents.ShowDialog)
                    }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Make Notes")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        if(state.isAddingTodo){
            AddTodoField(state,event)
        }
        if(state.todoList.isEmpty()){
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "There is no tasks." ,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }else{
            LazyColumn(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                items(state.todoList){
                        todo ->
                    Spacer(modifier = modifier.height(5.dp))
                    TodoField(
                        state,
                        events = event,
                        todo = todo
                    )
                }
            }
        }
    }
}
