package com.gilosher.colorpalettes.features.color_palette

import com.gilosher.colorpalettes.features.color_palette.model.ColorPalette
import com.gilosher.colorpalettes.features.color_palette.model.toColorPalette
import com.gilosher.colorpalettes.network.ColormindApi
import com.gilosher.colorpalettes.network.RandomColorRequestBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ColorsRepo @Inject constructor(
    private val api: ColormindApi
) {
    suspend fun generateColor(): ColorPalette? = withContext(Dispatchers.IO) {
        return@withContext try {
            val body = RandomColorRequestBody()
            val response = api.getRandomColors(body)
            val colorPalette = response.result.toColorPalette()
            colorPalette
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
