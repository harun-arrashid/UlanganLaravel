package net.s1mple.ulanganlaravel.data.model.transaction

import com.google.gson.annotations.SerializedName

data class ResponseTransactionList(

	@field:SerializedName("data")
	val data: List<DataTransaction>? = null
)

data class DataTransaction(

	@field:SerializedName("kd_transaksi")
	val kdTransaksi: Int? = null,

	@field:SerializedName("kd_agen")
	val kdAgen: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("total_rupiah")
	val totalRupiah: String? = null,

	@field:SerializedName("no_faktur")
	val noFaktur: String? = null,

	@field:SerializedName("tgl_penjualan")
	val tglPenjualan: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
