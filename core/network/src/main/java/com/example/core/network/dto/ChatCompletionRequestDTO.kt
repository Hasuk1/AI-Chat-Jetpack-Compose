package com.example.core.network.dto

import com.example.core.network.dto.model.Message
import com.google.gson.annotations.SerializedName

data class ChatCompletionRequestDTO(

  @SerializedName("model")
  val model: String = "gpt-3.5-turbo",

  @SerializedName("messages")
  val messages: List<Message>
)