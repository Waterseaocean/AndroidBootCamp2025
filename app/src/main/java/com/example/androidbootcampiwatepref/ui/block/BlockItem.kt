package com.example.androidbootcampiwatepref.ui.block

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androidbootcampiwatepref.R
import kotlinx.coroutines.delay

@Composable
fun BlockItem(
    initialState: Int = 0
) {
    var state by remember { mutableStateOf(initialState) }
    var particles by remember { mutableStateOf<List<Particle>>(emptyList()) }

    Box(
        modifier = Modifier
            .size(100.dp)
            .clickable {
                if (state < 2) {
                    if (state == 1) {
                        particles = generateParticles()
                    }
                    state += 1
                }
            }
    ) {
        when (state) {
            0 -> Image(painter = painterResource(id = R.drawable.block_normal), contentDescription = null)
            1 -> Image(painter = painterResource(id = R.drawable.block_damage), contentDescription = null)
            2 -> Box(modifier = Modifier.fillMaxSize())
        }

        if (particles.isNotEmpty()) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                particles.forEach { p ->
                    drawRect(Color.Gray.copy(alpha = p.alpha), Offset(p.x, p.y), Size(6f, 6f))
                }
            }

            LaunchedEffect(particles) {
                repeat(18) {
                    particles = particles.map { p ->
                        p.copy(
                            x = p.x + p.vx,
                            y = p.y + p.vy,
                            alpha = (p.alpha - 0.06f).coerceAtLeast(0f) // <- ここ
                        )
                    }
                    delay(16L)
                }
                particles = emptyList()
            }
        }
    }
}
