package com.gilosher.colorpalettes.features.color_palette

import com.gilosher.colorpalettes.db.core.AppDatabase
import com.gilosher.colorpalettes.db.model.toApp
import com.gilosher.colorpalettes.db.model.toDb
import com.gilosher.colorpalettes.features.color_palette.model.ColorPalette
import com.gilosher.colorpalettes.features.color_palette.model.toColorPalette
import com.gilosher.colorpalettes.network.ColormindApi
import com.gilosher.colorpalettes.network.RandomColorRequestBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ColorsRepo @Inject constructor(
    private val api: ColormindApi,
    private val db: AppDatabase
) {
    fun getAllPalettes() = db.colorPaletteDao().getAllPalettes().map { palettesList ->
        palettesList.map { colorPaletteDb ->
            colorPaletteDb.toApp()
        }
    }

    suspend fun generateColor(): ColorPalette? = withContext(Dispatchers.IO) {
        return@withContext try {
            val body = RandomColorRequestBody()
            val response = api.getRandomColors(body)
            response.result.toColorPalette()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun addColorPalette(palette: ColorPalette) = withContext(Dispatchers.IO) {
        db.colorPaletteDao().insertPalette(palette.toDb())
    }

    suspend fun deletePalette(colorPalette: ColorPalette) = withContext(Dispatchers.IO) {
        db.colorPaletteDao().deletePalette(colorPalette.toDb())
    }
}
