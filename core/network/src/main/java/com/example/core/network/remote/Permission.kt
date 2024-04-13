package com.example.core.network.remote

import com.google.gson.annotations.SerializedName

data class Permission(
  @SerializedName("id")
  var id: String? = null,
  @SerializedName("object")
  var modelPermissionObject: String? = null,
  @SerializedName("created")
  var created: Int? = null,
  @SerializedName("allow_create_engine")
  var allowCreateEngine: Boolean? = null,
  @SerializedName("allow_sampling")
  var allowSampling: Boolean? = null,
  @SerializedName("allow_logprobs")
  var allowLogprobs: Boolean? = null,
  @SerializedName("allow_search_indices")
  var allowSearchIndices: Boolean? = null,
  @SerializedName("allow_view")
  var allowView: Boolean? = null,
  @SerializedName("allow_fine_tuning")
  var allowFineTuning: Boolean? = null,
  @SerializedName("organization")
  var organization: String? = null,
  @SerializedName("group")
  var group: String? = null,
  @SerializedName("is_blocking")
  var isBlocking: Boolean? = null
)