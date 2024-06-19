package com.gilosher.colorpalettes.features.create_palette.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilosher.colorpalettes.features.color_palette.ColorsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePaletteViewModel @Inject constructor(
    private val colorsRepo: ColorsRepo
) : ViewModel() {

    private val _screenState = MutableStateFlow(CreatePaletteScreenState())
    val screenState = _screenState.asStateFlow()

    init {
        generatePalette()
    }

    fun onEvent(event: CreatePaletteEvent) {
        when (event) {
            CreatePaletteEvent.OnGeneratePaletteClicked -> generatePalette()
            CreatePaletteEvent.SavePaletteClicked -> savePalette()
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
            _screenState.update { state ->
                state.copy(
                    loading = false,
                    palette = colorPalette
                )
            }
        }
    }

    private fun savePalette() {

    }
}