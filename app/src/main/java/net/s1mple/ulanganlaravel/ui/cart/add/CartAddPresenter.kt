package net.s1mple.ulanganlaravel.ui.cart.add

import net.s1mple.ulanganlaravel.data.model.cart.ResponseCartUpdate
import net.s1mple.ulanganlaravel.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartAddPresenter(val view: CartAddContract.View): CartAddContract.Presenter {

    override fun addCart(username: String, kd_produk: Long, jumlah: Long) {
        view.onLoading(true)
        ApiService.endpoint.addCart(username, kd_produk, jumlah).enqueue(object : Callback<ResponseCartUpdate> {

            override fun onResponse(call: Call<ResponseCartUpdate>, response: Response<ResponseCartUpdate>) {
                view.onLoading(false)
                if (response.isSuccessful) response.body()?.let {
                    view.onResult(it)
                    view.showMessage(it.msg ?: "")
                }
            }

            override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {
                view.onLoading(false)
            }
        })
    }
}