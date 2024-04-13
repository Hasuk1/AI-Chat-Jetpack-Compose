package com.example.core.network.dto.model

import com.google.gson.annotations.SerializedName

data class Choice(
  @SerializedName("finish_reason")
  val finishReason: String? = null,
  @SerializedName("index")
  val index: Int? = null,
  @SerializedName("message")
  val message: Message? = null
)
