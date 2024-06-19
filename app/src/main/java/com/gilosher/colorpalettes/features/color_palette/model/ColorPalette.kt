package com.gilosher.colorpalettes.features.color_palette.model

import androidx.compose.ui.graphics.Color

data class ColorPalette(
    val colors: List<Color>
)

fun List<List<Int>>.toColorPalette() =
    ColorPalette(
        colors = map { color ->
            Color(
                red = color[0],
                green = color[1],
                blue = color[2],
            )
        }
    )