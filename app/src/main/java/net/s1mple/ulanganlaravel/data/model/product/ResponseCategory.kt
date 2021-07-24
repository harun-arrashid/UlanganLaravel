package net.s1mple.ulanganlaravel.data.model.product

import com.google.gson.annotations.SerializedName

data class ResponseCategory(

    @field:SerializedName("data")
    val data: List<DataCategory>? = null
)

data class DataCategory(

    @field:SerializedName("kd_kategori")
    val kdKategori: Int? = null,

    @field:SerializedName("kategori")
    val kategori: String? = null,

    @field:SerializedName("gambar_kategori")
    val gambarKategori: String? = null
)