package net.s1mple.ulanganlaravel.data.model.agent

import com.google.gson.annotations.SerializedName

data class ResponseAgentList(

	@field:SerializedName("data")
	val data: List<DataAgent>? = null
)

data class DataAgent(

	@field:SerializedName("nama_pemilik")
	val namaPemilik: String? = null,

	@field:SerializedName("kd_agen")
	val kdAgen: Int? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("gambar_toko")
	val gambarToko: String? = null,

	@field:SerializedName("nama_toko")
	val namaToko: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)
