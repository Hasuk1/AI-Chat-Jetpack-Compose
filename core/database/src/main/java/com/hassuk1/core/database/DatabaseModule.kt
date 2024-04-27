package com.hassuk1.core.database

import android.content.Context
import androidx.room.Room
import com.hassuk1.core.database.dao.ChatsDao
import com.hassuk1.core.database.dao.MessagesDao
import com.hassuk1.core.database.dao.UserDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ViewModelComponent::class)
@Module
object DatabaseModule {
  @Provides
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

  @Provides
  fun provideChatsDao(database: AppDatabase): ChatsDao {
    return database.chatsDao()
  }

  @Provides
  fun provideMessagesDao(database: AppDatabase): MessagesDao {
    return database.messageDao()
  }
}
