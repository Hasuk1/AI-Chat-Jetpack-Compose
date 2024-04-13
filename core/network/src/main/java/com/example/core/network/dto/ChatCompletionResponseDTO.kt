package com.example.core.network.dto

import com.example.core.network.dto.model.Choice
import com.example.core.network.dto.model.Usage
import com.google.gson.annotations.SerializedName

data class ChatCompletionResponseDTO(
  @SerializedName("choices")
  val choices: List<Choice?>? = null,
  @SerializedName("created")
  val created: Int? = null,
  @SerializedName("id")
  val id: String? = null,
  @SerializedName("model")
  val model: String? = null,
  @SerializedName("object")
  val objectX: String? = null,
  @SerializedName("usage")
  val usage: Usage? = null
)