package com.gilosher.colorpalettes.features.create_palette.data

import com.gilosher.colorpalettes.features.color_palette.model.ColorPalette

data class CreatePaletteScreenState(
    val loading: Boolean = false,
    val palette: ColorPalette? = null
)