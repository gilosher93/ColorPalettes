package com.gilosher.colorpalettes.db.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gilosher.colorpalettes.features.color_palette.model.ColorPalette

@Entity(tableName = "colors")
data class ColorPaletteDb(
    @PrimaryKey
    val id: String,
    val timestamp: Long,
    val colors: String,
)

fun ColorPalette.toDb() = ColorPaletteDb(
    id = id,
    timestamp = timestamp,
    colors = colors.map { it.toArgb() }.joinToString(",")
)

fun ColorPaletteDb.toApp() = ColorPalette(
    id = id,
    timestamp = timestamp,
    colors = colors.split(",").map { Color(it.toInt()) }
)

