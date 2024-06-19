package com.gilosher.colorpalettes.navigation

sealed class ScreenRoute(val route: String) {
    data object PaletteList : ScreenRoute(Route.PALETTE_LIST.name)
    data object CreatePalette : ScreenRoute(Route.CREATE_PALETTE.name)
}

enum class Route {
    PALETTE_LIST,
    CREATE_PALETTE
}