package com.example.core.network.dto.model

import com.google.gson.annotations.SerializedName

data class Usage(
  @SerializedName("completion_tokens")
  val completionTokens: Int? = null,
  @SerializedName("prompt_tokens")
  val promptTokens: Int? = null,
  @SerializedName("total_tokens")
  val totalTokens: Int? = null
)