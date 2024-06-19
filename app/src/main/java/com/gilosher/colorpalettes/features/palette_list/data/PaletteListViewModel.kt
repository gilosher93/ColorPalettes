package com.gilosher.colorpalettes.features.palette_list.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilosher.colorpalettes.features.color_palette.ColorsRepo
import com.gilosher.colorpalettes.features.color_palette.model.ColorPalette
import com.gilosher.colorpalettes.features.palette_list.data.model.PaletteListEvent
import com.gilosher.colorpalettes.features.palette_list.data.model.PaletteListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaletteListViewModel @Inject constructor(
    private val colorsRepo: ColorsRepo
) : ViewModel() {
    private val _screenState = MutableStateFlow(PaletteListScreenState(loading = true))
    val screenState = _screenState.asStateFlow()

    init {
        observeColorPalettes()
    }

    fun onEvent(event: PaletteListEvent) {
        when (event) {
            is PaletteListEvent.DeletePalette -> deletePalette(event.colorPalette)
        }
    }

    private fun deletePalette(colorPalette: ColorPalette) {
        viewModelScope.launch {
            colorsRepo.deletePalette(colorPalette)
        }
    }

    private fun observeColorPalettes() {
        viewModelScope.launch {
            colorsRepo.getAllPalettes().collect { colorPalettes ->
                _screenState.update { state ->
                    state.copy(
                        loading = false,
                        paletteList = colorPalettes
                    )
                }
            }
        }
    }
}