package com.example.core.network.dto.model

import com.google.gson.annotations.SerializedName

data class Message(
  @SerializedName("content")
  val content: String? = null,
  @SerializedName("role")
  val role: String? = null
)
