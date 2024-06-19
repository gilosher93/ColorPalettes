package com.gilosher.colorpalettes.features.create_palette.data

sealed class CreatePaletteEvent {
    data object OnGeneratePaletteClicked : CreatePaletteEvent()
    data object SavePaletteClicked : CreatePaletteEvent()
}