package com.gilosher.colorpalettes.features.create_palette.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.gilosher.colorpalettes.features.create_palette.data.CreatePaletteViewModel

@Composable
fun CreatePaletteScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<CreatePaletteViewModel>()

    CreatePaletteScreenContent(
        modifier = modifier
    )
}

@Composable
fun CreatePaletteScreenContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {

    }
}

@Preview
@Composable
private fun CreatePaletteScreenPreview() {
    CreatePaletteScreenContent()
}