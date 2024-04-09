package com.hassuk1.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
object DatabaseModule {
  @Provides
  @ViewModelScoped
  fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(
      context.applicationContext,
      AppDatabase::class.java, "app_database"
    ).build()
  }

  @Provides
  fun provideUserDao(database: AppDatabase): UserDataDao {
    return database.userDao()
  }
}
