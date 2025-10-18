package com.example.androidbootcampiwatepref

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidbootcampiwatepref.ui.theme.AndroidBootcampIwatePrefTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBootcampIwatePrefTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
////                    CountUp(
////                        modifier = Modifier.padding()
////                    )
////                    MyLayout()
//                    HomeScreen()
//                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Home,
                    ) {
                        composable<Routes.Home> {
                            HomeScreen(
                                modifier = Modifier.fillMaxSize(),
                                navigateTo = { route ->
                                    navController.navigate(route)
                                }
                            )
                        }
                        composable<Routes.Search> {
                            SearchScreen(
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun CountUp(
//    modifier: Modifier = Modifier,
//) {
//    var count = remember { 0 }
//    Column(
//        modifier = modifier,
//    ) {
//        Text("count: $count")
//        Button(
//            onClick = {
//                count++
//            }
//        ) {
//            Text("Count Up!")
//        }
//    }
//}
//
//@Composable
//fun MyLayout() {
//    Column {
//        Row {
//            Text("Row1")
//            Spacer(modifier = Modifier.width(12.dp))
//            Text("Row1の説明")
//        }
//        Row {
//            Text("Row2")
//            Spacer(modifier = Modifier.width(12.dp))
//            Text("Row2の説明")
//        }
//        Row {
//            Text("Row3")
//            Spacer(modifier = Modifier.width(12.dp))
//            Text("Row3の説明")
//        }
//    }
//}

//@Composable
//fun MyLayout() {
//    Column {
//        (1..3).forEach {
//            Item(it)
//        }
//    }
//}
//
//@Composable
//fun Item(count: Int) {
//    Row {
//        Text("Row$count")
//        Spacer(modifier = Modifier.width(12.dp))
//        Text("Row${count}の説明")
//    }
//}

// オリジナル
//@Composable
//fun MyLayout() {
//    Column {
//        (1..3).forEach {
//            Row {
//                Text("Row$it")
//                Spacer(modifier = Modifier.width(12.dp))
//                Text("Row${it}の説明")
//            }
//        }
//    }
//}

//@Composable
//fun HomeScreen(
//    modifier: Modifier = Modifier,
//) {
//    Box(
//        modifier = modifier.verticalScroll(rememberScrollState()),
//    ) {
//        MyLayout()
//    }
//}
//
//@Composable
//fun MyLayout() {
//    Column {
//        (0..<1000).forEach {
//            Item(it)
//        }
//    }
//}
//
//@Composable
//fun Item(count: Int) {
//    Row {
//        Text("Row$count")
//        Spacer(modifier = Modifier.width(12.dp))
//        Text("Row${count}の説明")
//    }
//}

//@Composable
//fun HomeScreen(
//    modifier: Modifier = Modifier,
//) {
//    Box(
//        modifier = modifier, // verticalScrollは削除する
//    ) {
//        MyLayout()
//    }
//}
//
//private const val contentTypeOfItem = "CONTENT_TYPE_OF_ITEM"
//
//@Composable
//fun MyLayout() {
//    LazyColumn {
//        items(
//            count = 100000,
//            key = { index -> index },
//            contentType = { contentTypeOfItem },
//        ) { count ->
//            Item(count)
//        }
//    }
//}

// fun Item(count: Int) の記述はそのまま

sealed interface Routes {
    @Serializable
    data object Home : Routes

    @Serializable
    data object Search : Routes
}
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateTo: (route: Routes) -> Unit, // 追加
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text ="Home Screen")
            Button(onClick = { navigateTo(Routes.Search) }) { // 追加
                Text(text = "Go to Search Screen")
            }
        }
    }
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Search Screen")
    }
}