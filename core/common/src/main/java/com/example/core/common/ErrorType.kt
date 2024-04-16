package com.example.core.common

enum class ErrorType(val message: String) {
  INCORRECT_API("Invalid Api Key"), CONNECTED_FAIL("Connected fail"), UNSPECIFIED("Unspecified error"), NONE(
    "All good"
  )
}