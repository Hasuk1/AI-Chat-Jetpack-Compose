package com.example.core.network

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class BaseUrlInterceptor : Interceptor {
  private var _newBaseUrl: HttpUrl? = null

  fun setNewBaseUrl(baseUrl: HttpUrl) {
    _newBaseUrl = baseUrl
    Log.d("MyLog", "NewBaseUrl=$baseUrl")
  }

  override fun intercept(chain: Interceptor.Chain): Response {
    var request = chain.request()
    _newBaseUrl?.let { baseUrl ->
      val newUrl = request.url.newBuilder()
        .scheme(baseUrl.scheme)
        .host(baseUrl.host)
        .port(baseUrl.port)
        .build()
      request = request.newBuilder().url(newUrl).build()
    }
    return chain.proceed(request)
  }
}