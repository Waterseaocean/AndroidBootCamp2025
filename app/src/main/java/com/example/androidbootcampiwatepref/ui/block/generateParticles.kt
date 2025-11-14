package com.example.androidbootcampiwatepref.ui.block

fun generateParticles(): List<Particle> {
    val list = mutableListOf<Particle>()
    repeat(12) {// 破片12個生成
        list.add(
            Particle(
                x = 45f + (-10..10).random(), // 中心付近から飛び散る
                y = 45f + (-10..10).random(),
                vx = (-2..2).random().toFloat(),
                vy = (-3..1).random().toFloat(),
                alpha = 1f
            )
        )
    }
    return list
}