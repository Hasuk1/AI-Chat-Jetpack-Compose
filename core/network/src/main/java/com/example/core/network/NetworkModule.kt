package com.example.core.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {
  @Provides
  @ViewModelScoped
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  @Provides
  @ViewModelScoped
  fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor,
    baseUrlInterceptor: BaseUrlInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(baseUrlInterceptor)
      .build()
  }

  @Provides
  @ViewModelScoped
  fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
      .baseUrl("https://eu.neuroapi.host/").client(client).build()
  }

  @Provides
  fun provideDummyJsonApi(retrofit: Retrofit): AiChatApi {
    return retrofit.create(AiChatApi::class.java)
  }

  @Provides
  @ViewModelScoped
  fun provideBaseUrlInterceptor(): BaseUrlInterceptor {
    return BaseUrlInterceptor()
  }
}