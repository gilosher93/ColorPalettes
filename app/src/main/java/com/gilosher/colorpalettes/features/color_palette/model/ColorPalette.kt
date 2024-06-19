package com.gilosher.colorpalettes.features.color_palette.model

import androidx.compose.ui.graphics.Color
import java.util.UUID

data class ColorPalette(
    val id: String,
    val timestamp: Long,
    val colors: List<Color>
)

fun List<List<Int>>.toColorPalette() =
    ColorPalette(
        id = UUID.randomUUID().toString(),
        timestamp = System.currentTimeMillis(),
        colors = map { color ->
            Color(
                red = color[0],
                green = color[1],
                blue = color[2],
            )
        }
    )