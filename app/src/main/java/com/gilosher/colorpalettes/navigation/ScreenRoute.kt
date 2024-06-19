package com.gilosher.colorpalettes.navigation

import kotlinx.serialization.Serializable

sealed class ScreenRoute {

    @Serializable
    data object PaletteList: ScreenRoute()

    @Serializable
    data object CreatePalette: ScreenRoute()
}