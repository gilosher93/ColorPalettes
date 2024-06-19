package com.gilosher.colorpalettes.features.palette_list.data.model

import com.gilosher.colorpalettes.features.color_palette.model.ColorPalette

data class PaletteListScreenState(
    val loading: Boolean = false,
    val paletteList: List<ColorPalette> = emptyList(),
)