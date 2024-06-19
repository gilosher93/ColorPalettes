package com.gilosher.colorpalettes.db.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gilosher.colorpalettes.db.dao.ColorPaletteDao
import com.gilosher.colorpalettes.db.model.ColorPaletteDb
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Database(
    entities = [
        ColorPaletteDb::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    class Factory @Inject constructor(
        @ApplicationContext private val context: Context
    ) {
        fun create() = Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                "${DatabaseConstants.NAME}.db"
            )
            .build()
    }

    abstract fun colorPaletteDao(): ColorPaletteDao
}
