package com.example.core.network.remote

import com.google.gson.annotations.SerializedName

data class ModelResponse(
  @SerializedName("data")
  var data: ArrayList<Data> = arrayListOf(),
  @SerializedName("object")
  var objectType: String? = null
)