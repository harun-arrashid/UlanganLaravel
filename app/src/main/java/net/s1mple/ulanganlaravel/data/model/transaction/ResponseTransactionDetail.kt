package net.s1mple.ulanganlaravel.data.model.transaction

import com.google.gson.annotations.SerializedName

data class ResponseTransactionDetail(

	@field:SerializedName("data")
	val data: List<DataDetail>? = null
)

data class DataDetail(

	@field:SerializedName("harga_rupiah")
	val hargaRupiah: String? = null,

	@field:SerializedName("nama_produk")
	val namaProduk: String? = null,

	@field:SerializedName("jumlah")
	val jumlah: Int? = null,

	@field:SerializedName("harga")
	val harga: Int? = null,

	@field:SerializedName("kd_transaksi_detail")
	val kdTransaksiDetail: Int? = null,

	@field:SerializedName("kd_produk")
	val kdProduk: Int? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("gambar_produk")
	val gambarProduk: String? = null,

	@field:SerializedName("no_faktur")
	val noFaktur: String? = null
)
