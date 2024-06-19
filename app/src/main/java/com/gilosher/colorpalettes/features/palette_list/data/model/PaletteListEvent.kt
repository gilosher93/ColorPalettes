package com.gilosher.colorpalettes.features.palette_list.data.model

import com.gilosher.colorpalettes.features.color_palette.model.ColorPalette

sealed class PaletteListEvent {
    data class DeletePalette(val colorPalette: ColorPalette) : PaletteListEvent()
}