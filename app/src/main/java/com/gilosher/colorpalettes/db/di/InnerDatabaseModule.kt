package com.gilosher.colorpalettes.db.di

import com.gilosher.colorpalettes.db.core.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class InnerDatabaseModule {

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(factory: AppDatabase.Factory): AppDatabase = factory.create()
    }
}
