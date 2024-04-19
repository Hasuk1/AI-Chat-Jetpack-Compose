package com.hassuk1.core.model

data class Chat(
  val id: Int,
  val name: String,
  val firstPromt: String,
  val isRemoved: Boolean
)

/*data class Chat(
  val chat: Any,
  val description: String,
  val isRemoved: Boolean
)*/