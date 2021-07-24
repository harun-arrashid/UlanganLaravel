package net.s1mple.ulanganlaravel.network

import net.s1mple.ulanganlaravel.data.ResponseLogin
import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentDetail
import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentList
import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentUpdate
import net.s1mple.ulanganlaravel.data.model.cart.ResponseCartList
import net.s1mple.ulanganlaravel.data.model.cart.ResponseCartUpdate
import net.s1mple.ulanganlaravel.data.model.product.ResponseCategory
import net.s1mple.ulanganlaravel.data.model.product.ResponseProduct
import net.s1mple.ulanganlaravel.data.model.transaction.ResponseTransactionDetail
import net.s1mple.ulanganlaravel.data.model.transaction.ResponseTransactionList
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {

    @FormUrlEncoded
    @POST("login_pegawai")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @GET("agen")
    fun getAgent(): Call<ResponseAgentList>

    @Multipart
    @POST("agen")
    fun insertAgent(
        @Query("nama_toko") nama_toko: String,
        @Query("nama_pemilik") nama_pemilik: String,
        @Query("alamat") alamat: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part gambar_toko: MultipartBody.Part
    ): Call<ResponseAgentUpdate>

    @GET("agen/{id}")
    fun getAgentDetail(
        @Path("id") kd_agen: Long
    ): Call<ResponseAgentDetail>

    @Multipart // klo uploadnya mengandung FIle/Img hrs  make ini
    @POST("agen/{id}")
    fun updateAgent(
        @Path("id") kd_agen: Long,
        @Query("nama_toko") nama_toko: String,
        @Query("nama_pemilik") nama_pemilik: String,
        @Query("alamat") alamat: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part gambar_toko: MultipartBody.Part,
        @Query("_method") _method: String
    ): Call<ResponseAgentUpdate>

    @DELETE("agen/{id}")
    fun deleteAgent(
        @Path("id") id: Long
    ): Call<ResponseAgentUpdate>

    @FormUrlEncoded
    @POST("get_transaksi")
    fun getTransactionByUsername(
        @Field("username") username: String
    ): Call<ResponseTransactionList>

    @FormUrlEncoded
    @POST("get_detail_transaksi")
    fun getTransactionByInvoice(
        @Field("no_faktur") no_faktur: String
    ): Call<ResponseTransactionDetail>

    @FormUrlEncoded
    @POST("get_cart")
    fun getCart(
        @Field("username") username: String
    ): Call<ResponseCartList>

    @FormUrlEncoded
    @POST("add_cart")
    fun addCart(
        @Field("username") username: String,
        @Field("kd_produk") kd_produk: Long,
        @Field("jumlah") jumlah: Long
    ): Call<ResponseCartUpdate>

    @GET("get_kategori")
    fun getCategory(): Call<ResponseCategory>

    @FormUrlEncoded
    @POST("get_produk")
    fun getProductByCategory(
        @Field("kd_kategori") kd_kategori: Long
    ): Call<ResponseProduct>

    @FormUrlEncoded
    @POST("delete_item_cart")
    fun deleteItemCart(
        @Field("kd_keranjang") kd_keranjang: Long
    ): Call<ResponseCartUpdate>

    @FormUrlEncoded
    @POST("delete_cart")
    fun deleteCart(
        @Field("username") username: String
    ): Call<ResponseCartUpdate>

    @GET("search_agen")
    fun searchAgent(
        @Query("keyword") keyword: String
    ): Call<ResponseAgentList>
}