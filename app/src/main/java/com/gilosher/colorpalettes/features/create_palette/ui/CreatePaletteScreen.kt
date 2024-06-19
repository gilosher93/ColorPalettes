package com.gilosher.colorpalettes.features.create_palette.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gilosher.colorpalettes.R
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
        selectBgColor = { color ->
            viewModel.onEvent(CreatePaletteEvent.OnColorSelected(color))
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
    selectBgColor: (Color) -> Unit = {},
    savePalette: () -> Unit = {},
    navigateBack: () -> Unit = {},
) {
    val initialBgColor = MaterialTheme.colorScheme.background
    val bgColorValue by remember(screenState.selectedBgColor) {
        mutableStateOf(screenState.selectedBgColor ?: initialBgColor)
    }
    val bgColor by animateColorAsState(
        targetValue = bgColorValue,
        label = "bg color",
        animationSpec = tween(durationMillis = 1000)
    )

    Column(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .fillMaxSize()
            .background(bgColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.create_color_palette_title))
            },
            navigationIcon = {
                IconButton(onClick = { navigateBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "back button"
                    )
                }
            }
        )
        Box(
            modifier = Modifier.size(60.dp)
        ) {
            if (screenState.loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(10.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            enabled = !screenState.loading,
            onClick = {
                regenerateColor()
            }
        ) {
            Text(text = stringResource(R.string.regenerate_color_button), fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.height(100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            screenState.palette?.colors.orEmpty().forEach {
                Box(
                    modifier = Modifier
                        .size(65.dp)
                        .bouncingClickable {
                            selectBgColor(it)
                        }
                        .border(
                            width = 0.5.dp,
                            color = Color.White.copy(alpha = 0.5f)
                        )
                        .background(color = it)

                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(16.dp)
                .fillMaxWidth()
                .height(56.dp),
            enabled = !screenState.loading,
            onClick = {
                savePalette()
            }
        ) {
            Text(text = stringResource(R.string.save_button), fontSize = 20.sp)
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