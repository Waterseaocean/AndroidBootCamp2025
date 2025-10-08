package com.example.androidbootcampiwatepref

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidbootcampiwatepref.ui.theme.AndroidBootcampIwatePrefTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBootcampIwatePrefTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoApp()
                }
            }
        }
    }
}

// Todoリストのデータクラス
data class TodoItem(
    val id: Int,             // 各タスクを識別するためのID（連番）
    val title: String,       // タスクの内容
    var isDone: Boolean = false // チェックのあるなし
)

@Composable
fun TodoApp() {
    // remember を使って状態を保持する
    // 初期状態に3つのタスクを入れておく
    var todos by remember {
        mutableStateOf(
            listOf(
                TodoItem(1, "プログラミングする"),
                TodoItem(2, "課題やる"),
                TodoItem(3, "サークル行く")
            )
        )
    }
    var newTodoText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "ToDoリスト",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Todoリスト表示部分
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(todos) { todo ->
                TodoRow(todo = todo, onToggle = { // toggleで切り替える
                    // チェック状態を切り替える
                    todos = todos.map {
                        if (it.id == todo.id) it.copy(isDone = !it.isDone) else it
                    }
                })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 追加
        Row {
            // タスク名記入欄
            TextField(
                value = newTodoText,
                onValueChange = { newTodoText = it },
                placeholder = { Text("新しいタスクを入力") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            // 追加ボタン
            Button(onClick = {
                if (newTodoText.isNotBlank()) {
                    val newTodo = TodoItem(id = todos.size + 1, title = newTodoText)
                    todos = todos + newTodo
                    newTodoText = ""
                }
            }) {
                Text("追加")
            }
        }
    }
}

// 1行のタスク表示部分
@Composable
fun TodoRow(todo: TodoItem, onToggle: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = if (todo.isDone) "✅ ${todo.title}" else "⬜ ${todo.title}",
            style = MaterialTheme.typography.bodyLarge
        )
        Button(onClick = onToggle) {
            Text(if (todo.isDone) "戻す" else "完了")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTodoApp() {
    AndroidBootcampIwatePrefTheme {
        TodoApp()
    }
}