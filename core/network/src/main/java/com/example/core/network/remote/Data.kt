package com.example.core.network.remote

import com.google.gson.annotations.SerializedName

data class Data(
  @SerializedName("id")
  var id: String? = null,
  @SerializedName("object")
  var modelObject: String? = null,
  @SerializedName("created")
  var created: Int? = null,
  @SerializedName("owned_by")
  var ownedBy: String? = null,
  @SerializedName("permission")
  var permission: ArrayList<Permission> = arrayListOf(),
  @SerializedName("root")
  var root: String? = null,
  @SerializedName("parent")
  var parent: String? = null
)