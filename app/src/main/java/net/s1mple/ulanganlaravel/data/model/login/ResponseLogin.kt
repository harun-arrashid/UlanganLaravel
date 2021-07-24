package net.s1mple.ulanganlaravel.data

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("pegawai")
	val pegawai: DataLogin? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataLogin(

	@field:SerializedName("jk")
	val jk: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("nama_pegawai")
	val namaPegawai: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("is_aktif")
	val isAktif: Int? = null
)
