package com.gilosher.colorpalettes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gilosher.colorpalettes.features.create_palette.ui.CreatePaletteScreen
import com.gilosher.colorpalettes.features.palette_list.ui.PaletteListScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ScreenRoute.PaletteList
    ) {
        composable<ScreenRoute.PaletteList> {
            PaletteListScreen(
                navigateToCreatePalette = {
                    navController.navigate(ScreenRoute.CreatePalette)
                }
            )
        }
        composable<ScreenRoute.CreatePalette> {
            CreatePaletteScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}