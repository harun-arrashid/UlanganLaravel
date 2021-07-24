package net.s1mple.ulanganlaravel.data.model.cart

import com.google.gson.annotations.SerializedName

data class ResponseCartUpdate(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)
