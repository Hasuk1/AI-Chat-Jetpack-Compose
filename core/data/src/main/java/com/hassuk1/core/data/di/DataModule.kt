package com.hassuk1.core.data.di

import com.hassuk1.core.data.repository.UserDataRepository
import com.hassuk1.core.data.repository.UserDataRepositoryImpl
import com.hassuk1.core.database.UserDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

  @Provides
  @ViewModelScoped
  fun provideUserDataRepository(userDataDao: UserDataDao): UserDataRepository {
    return UserDataRepositoryImpl(userDataDao)
  }
}