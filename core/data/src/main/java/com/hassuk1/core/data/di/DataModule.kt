package com.hassuk1.core.data.di

import com.example.core.network.AiChatApi
import com.example.core.network.BaseUrlInterceptor
import com.hassuk1.core.data.repository.ChatsRepository
import com.hassuk1.core.data.repository.ChatsRepositoryImpl
import com.hassuk1.core.data.repository.UserDataRepository
import com.hassuk1.core.data.repository.UserDataRepositoryImpl
import com.hassuk1.core.database.dao.ChatsDao
import com.hassuk1.core.database.dao.UserDataDao
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
  fun provideUserDataRepository(
    userDataDao: UserDataDao, api: AiChatApi, baseUrlInterceptor: BaseUrlInterceptor
  ): UserDataRepository {
    return UserDataRepositoryImpl(userDataDao, api, baseUrlInterceptor)
  }

  @Provides
  @ViewModelScoped
  fun provideChatsRepository(chatsDao: ChatsDao): ChatsRepository {
    return ChatsRepositoryImpl(chatsDao)
  }
}