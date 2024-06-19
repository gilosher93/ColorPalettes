package com.gilosher.colorpalettes.features.palette_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gilosher.colorpalettes.features.color_palette.model.ColorPalette

@Composable
fun ColorPaletteItem(
    modifier: Modifier = Modifier,
    colorPalette: ColorPalette,
    deletePalette: (ColorPalette) -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        colorPalette.colors.forEach {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(
                        width = 0.5.dp,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                    .background(color = it)

            )
        }
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = {
                deletePalette(colorPalette)
            }
        ) {
            Text(text = "Delete")
        }
    }
}

@Preview
@Composable
private fun ColorPaletteItemPreview() {
    ColorPaletteItem(
        colorPalette = ColorPalette(
            id = "",
            timestamp = System.currentTimeMillis(),
            colors = listOf(
                Color.Red,
                Color.Green,
                Color.Blue,
                Color.Red,
                Color.Green,
            )
        )
    )
}