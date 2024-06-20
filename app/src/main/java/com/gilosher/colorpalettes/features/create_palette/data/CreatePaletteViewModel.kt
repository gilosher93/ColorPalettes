package com.gilosher.colorpalettes.features.create_palette.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilosher.colorpalettes.features.color_palette.ColorsRepo
import com.gilosher.colorpalettes.features.color_palette.model.ColorPalette
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePaletteViewModel @Inject constructor(
    private val colorsRepo: ColorsRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _sideEffectChannel = Channel<CreatePaletteSideEffect>()
    val sideEffect = _sideEffectChannel.receiveAsFlow()

    private val _screenState = MutableStateFlow(
        CreatePaletteScreenState(
            selectedBgColor = savedStateHandle.get<Int?>(SELECTED_BG_COLOR_KEY)?.let { Color(it) },
            palette = savedStateHandle.get<String?>(COLOR_PALETTE_KEY)?.let {
                ColorPalette.create(
                    colors = it.split(",").map { color -> Color(color.toInt()) }
                )
            }
        )
    )
    val screenState = _screenState.asStateFlow()

    init {
        if (screenState.value.palette == null) {
            generatePalette()
        }
    }

    fun onEvent(event: CreatePaletteEvent) {
        when (event) {
            CreatePaletteEvent.OnGeneratePaletteClicked -> generatePalette()
            CreatePaletteEvent.SavePaletteClicked -> savePalette()
            is CreatePaletteEvent.OnColorSelected -> selectBgColor(event.color)
        }
    }

    private fun selectBgColor(color: Color) {
        savedStateHandle[SELECTED_BG_COLOR_KEY] = color.toArgb()
        _screenState.update { state ->
            state.copy(
                selectedBgColor = color
            )
        }
    }

    private fun generatePalette() {
        viewModelScope.launch {
            _screenState.update { state ->
                state.copy(
                    loading = true,
                    palette = null
                )
            }
            val colorPalette = colorsRepo.generateColor()
            savedStateHandle[COLOR_PALETTE_KEY] =
                colorPalette?.colors.orEmpty().map { it.toArgb() }.joinToString(",")
            _screenState.update { state ->
                state.copy(
                    loading = false,
                    palette = colorPalette
                )
            }
        }
    }

    private fun savePalette() {
        viewModelScope.launch {
            val palette = screenState.value.palette ?: return@launch
            _screenState.update { state -> state.copy(savingPalette = true) }
            colorsRepo.addColorPalette(palette)
            _sideEffectChannel.send(CreatePaletteSideEffect.NavigateBack)
        }
    }

    companion object {
        const val COLOR_PALETTE_KEY = "color_palette"
        const val SELECTED_BG_COLOR_KEY = "selected_bg_color"
    }
}