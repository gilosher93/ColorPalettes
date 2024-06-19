package com.gilosher.colorpalettes.features.create_palette.data

import androidx.compose.ui.graphics.Color
import com.gilosher.colorpalettes.features.color_palette.model.ColorPalette

data class CreatePaletteScreenState(
    val loading: Boolean = false,
    val palette: ColorPalette? = null,
    val selectedBgColor: Color? = null
)