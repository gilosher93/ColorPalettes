package com.gilosher.colorpalettes.features.create_palette.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gilosher.colorpalettes.features.create_palette.data.CreatePaletteEvent
import com.gilosher.colorpalettes.features.create_palette.data.CreatePaletteScreenState
import com.gilosher.colorpalettes.features.create_palette.data.CreatePaletteSideEffect
import com.gilosher.colorpalettes.features.create_palette.data.CreatePaletteViewModel
import com.gilosher.colorpalettes.ui.theme.ColorPalettesTheme
import com.gilosher.colorpalettes.utils.bouncingClickable

@Composable
fun CreatePaletteScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
) {
    val viewModel = hiltViewModel<CreatePaletteViewModel>()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                CreatePaletteSideEffect.NavigateBack -> navigateBack()
            }
        }
    }

    CreatePaletteScreenContent(
        screenState = screenState,
        modifier = modifier,
        regenerateColor = {
            viewModel.onEvent(CreatePaletteEvent.OnGeneratePaletteClicked)
        },
        savePalette = {
            viewModel.onEvent(CreatePaletteEvent.SavePaletteClicked)
        },
        navigateBack = navigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePaletteScreenContent(
    screenState: CreatePaletteScreenState,
    modifier: Modifier = Modifier,
    regenerateColor: () -> Unit = {},
    savePalette: () -> Unit = {},
    navigateBack: () -> Unit = {},
) {
    val initialBgColor = MaterialTheme.colorScheme.background
    var bgColorValue by remember { mutableStateOf(initialBgColor) }
    val bgColor by animateColorAsState(
        targetValue = bgColorValue,
        label = "bg color",
        animationSpec = tween(durationMillis = 1000)
    )

    Box(
        modifier = modifier
            .safeDrawingPadding()
            .fillMaxSize()
            .background(bgColor),
    ) {
        TopAppBar(
            title = {
                Text(text = "Create Color Palette")
            },
            navigationIcon = {
                IconButton(onClick = { navigateBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "back button"
                    )
                }
            },
            actions = {
                TextButton(
                    onClick = {
                        savePalette()
                    }
                ) {
                    Text(text = "Save", fontSize = 16.sp)
                }
            }
        )
        if (screenState.loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
        screenState.palette?.let { palette ->
            Row(
                modifier = modifier.align(Alignment.Center),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                palette.colors.forEach {
                    Box(
                        modifier = Modifier
                            .size(65.dp)
                            .bouncingClickable {
                                bgColorValue = it
                            }
                            .border(
                                width = 0.5.dp,
                                color = Color.White.copy(alpha = 0.5f)
                            )
                            .background(color = it)

                    )
                }
            }
        }
        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter),
            onClick = {
                bgColorValue = initialBgColor
                regenerateColor()
            }
        ) {
            Text(text = "Regenerate Color", fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
private fun CreatePaletteScreenPreview() {
    ColorPalettesTheme {
        CreatePaletteScreenContent(
            screenState = CreatePaletteScreenState(
                loading = true
            )
        )
    }
}