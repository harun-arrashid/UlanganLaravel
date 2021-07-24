package net.s1mple.ulanganlaravel.data.model.agent

import com.google.gson.annotations.SerializedName

data class ResponseAgentUpdate(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)
