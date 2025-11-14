package com.example.androidbootcampiwatepref

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidbootcampiwatepref.ui.block.BlockItem
import com.example.androidbootcampiwatepref.ui.block.Particle
import com.example.androidbootcampiwatepref.ui.block.generateParticles
import com.example.androidbootcampiwatepref.ui.theme.AndroidBootcampIwatePrefTheme
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // 既存の setContent
            AndroidBootcampIwatePrefTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // mainの記述
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(
                                modifier = Modifier.fillMaxSize(),
                                navigateTo = { route -> navController.navigate("block") }
                            )
                        }
                        composable("block") {
                            BlockScreen(
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed interface Routes {
    @Serializable
    data object Home : Routes

    @Serializable
    data object Block : Routes
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateTo: (route: Routes) -> Unit,
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
            Text(text = "ブロック破壊ゲーム")
            Button(onClick = { navigateTo(Routes.Block) }) {
                Text(text = "Go to Block Screen")
            }
        }
    }
}

@Composable
fun BlockScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        BlockLayout()
    }
}

private const val contentTypeOfItem = "CONTENT_TYPE_OF_ITEM"

@Composable
fun BlockLayout() {
    // 各セル（3000個）の状態を保持。0=通常, 1=ひび割れ状態, 2=壊れた（透明にする）
    val cellStates = remember {
        // mutableStateListOf：mutableStateOfのリスト版
        mutableStateListOf<Int>().apply {
            repeat(3000) {
                add(0)
            }
        }
    }

    LazyColumn {
        items(100) { rowIndex ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (colIndex in 0..2) {
                    BlockItem()
                }
            }
        }
    }
}