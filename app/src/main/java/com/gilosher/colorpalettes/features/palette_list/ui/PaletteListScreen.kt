package com.gilosher.colorpalettes.features.palette_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gilosher.colorpalettes.R
import com.gilosher.colorpalettes.features.color_palette.model.ColorPalette
import com.gilosher.colorpalettes.features.palette_list.data.PaletteListViewModel
import com.gilosher.colorpalettes.features.palette_list.data.model.PaletteListEvent
import com.gilosher.colorpalettes.features.palette_list.data.model.PaletteListScreenState

@Composable
fun PaletteListScreen(
    modifier: Modifier = Modifier,
    navigateToCreatePalette: () -> Unit = {}
) {
    val viewModel = hiltViewModel<PaletteListViewModel>()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    PaletteListScreenContent(
        screenState = screenState,
        modifier = modifier,
        deletePalette = {
            viewModel.onEvent(PaletteListEvent.DeletePalette(it))
        },
        navigateToCreatePalette = navigateToCreatePalette
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaletteListScreenContent(
    screenState: PaletteListScreenState,
    modifier: Modifier = Modifier,
    deletePalette: (ColorPalette) -> Unit = {},
    navigateToCreatePalette: () -> Unit = {},
) {
    var paletteToDelete by remember {
        mutableStateOf<ColorPalette?>(null)
    }
    var showDeleteDialog by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .safeDrawingPadding()
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (screenState.loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Column {
            TopAppBar(title = { Text(text = "Color Palettes") })
            if (screenState.paletteList.isNotEmpty()) {
                LazyColumn {
                    items(screenState.paletteList) { colorPalette ->
                        ColorPaletteItem(
                            colorPalette = colorPalette,
                            deletePalette = {
                                paletteToDelete = colorPalette
                                showDeleteDialog = true
                            }
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = {
                navigateToCreatePalette()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = null
            )
        }

        if (showDeleteDialog) {
            DeletePaletteDialog(
                confirm = {
                    paletteToDelete?.let(deletePalette)
                        .also { paletteToDelete = null }
                    showDeleteDialog = false
                },
                onDismissRequest = {
                    showDeleteDialog = false
                }
            )
        }
    }
}

@Composable
private fun DeletePaletteDialog(
    onDismissRequest: () -> Unit = {},
    confirm: () -> Unit = {},

    ) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(R.string.delete_palette_title),
                fontSize = 16.sp
            )
        },
        text = {
            Text(
                text = stringResource(R.string.delete_palette_text),
                fontSize = 14.sp
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    confirm()
                }
            ) {
                Text(
                    text = stringResource(R.string.delete_palette_confirm),
                    fontSize = 14.sp
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissRequest() }
            ) {
                Text(
                    text = stringResource(R.string.delete_palette_cancel),
                    fontSize = 14.sp,
                )
            }
        }
    )
}

@Preview
@Composable
private fun PaletteListScreenPreview() {
    PaletteListScreenContent(
        screenState = PaletteListScreenState(
            loading = true,
        )
    )
}