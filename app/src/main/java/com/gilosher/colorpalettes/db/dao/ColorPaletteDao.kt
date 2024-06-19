package com.gilosher.colorpalettes.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gilosher.colorpalettes.db.model.ColorPaletteDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorPaletteDao {
    @Query("SELECT * FROM colors ORDER BY timestamp ASC")
    fun getAllPalettes(): Flow<List<ColorPaletteDb>>

    @Insert
    suspend fun insertPalette(colorPaletteDb: ColorPaletteDb)

    @Delete
    suspend fun deletePalette(colorPaletteDb: ColorPaletteDb)

}