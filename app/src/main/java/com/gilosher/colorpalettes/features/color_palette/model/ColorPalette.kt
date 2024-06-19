package com.gilosher.colorpalettes.features.color_palette.model

import androidx.compose.ui.graphics.Color
import java.util.UUID

data class ColorPalette(
    val id: String,
    val timestamp: Long,
    val colors: List<Color>
) {
    companion object {
        fun create(colors: List<Color>) = ColorPalette(
            id = UUID.randomUUID().toString(),
            timestamp = System.currentTimeMillis(),
            colors = colors
        )
    }
}

fun List<List<Int>>.toColorPalette() =
    ColorPalette.create(
        colors = map { color ->
            Color(
                red = color[0],
                green = color[1],
                blue = color[2],
            )
        }
    )