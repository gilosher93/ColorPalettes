package com.gilosher.colorpalettes.features.palette_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gilosher.colorpalettes.features.create_palette.data.CreatePaletteViewModel

@Composable
fun PaletteListScreen(
    modifier: Modifier = Modifier,
    navigateToCreatePalette: () -> Unit = {}
) {
    val viewModel = hiltViewModel<CreatePaletteViewModel>()

    PaletteListScreenContent(
        modifier = modifier,
        navigateToCreatePalette = navigateToCreatePalette
    )
}

@Composable
fun PaletteListScreenContent(
    modifier: Modifier = Modifier,
    navigateToCreatePalette: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                navigateToCreatePalette()
            }
        ) {
            Text(text = "Add")
        }
    }
}

@Preview
@Composable
private fun PaletteListScreenPreview() {
    PaletteListScreenContent()
}