package com.gilosher.colorpalettes.features.create_palette.data

sealed class CreatePaletteSideEffect {
    data object NavigateBack : CreatePaletteSideEffect()
}