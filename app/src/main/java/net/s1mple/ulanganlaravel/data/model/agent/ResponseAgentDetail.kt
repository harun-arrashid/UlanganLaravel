package net.s1mple.ulanganlaravel.data.model.agent

import com.google.gson.annotations.SerializedName

data class ResponseAgentDetail(
    @SerializedName("status") val status: Boolean,
    @SerializedName("msg") val msg: String,
    @SerializedName("data") val data: DataAgent
)