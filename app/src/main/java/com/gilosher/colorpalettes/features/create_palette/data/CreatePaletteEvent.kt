package com.gilosher.colorpalettes.features.create_palette.data

import androidx.compose.ui.graphics.Color

sealed class CreatePaletteEvent {
    data object OnGeneratePaletteClicked : CreatePaletteEvent()
    data object SavePaletteClicked : CreatePaletteEvent()
    data class OnColorSelected(val color: Color) : CreatePaletteEvent()
}