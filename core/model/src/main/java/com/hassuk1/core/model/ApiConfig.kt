package com.hassuk1.core.model

enum class ApiConfig(val apiName: String, val baseUrl: String, val docsUrl: String) {
  NEURO(
    apiName = "Neuro",
    baseUrl = "https://eu.neuroapi.host/",
    docsUrl = "https://neuroapi.gitbook.io/ru/osnovnoe/avtorizaciya-i-poluchenie-api-key"
  ),
  OPENAI(
    apiName = "OpenAI",
    baseUrl = "https://api.openai.com/",
    docsUrl = "https://platform.openai.com/api-keys"
  )
}