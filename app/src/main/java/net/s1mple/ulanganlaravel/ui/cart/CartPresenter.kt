package net.s1mple.ulanganlaravel.ui.cart

import net.s1mple.ulanganlaravel.data.model.cart.ResponseCartList
import net.s1mple.ulanganlaravel.data.model.cart.ResponseCartUpdate
import net.s1mple.ulanganlaravel.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartPresenter(val view: CartContract.View): CartContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getCart(username: String) {
        view.onLoading(true)
        ApiService.endpoint.getCart(username).enqueue(object : Callback<ResponseCartList> {

            override fun onResponse(call: Call<ResponseCartList>, response: Response<ResponseCartList>) {
                view.onLoading(false)
                if (response.isSuccessful) response.body()?.let {
                    view.onResultCart(it)
                }
            }

            override fun onFailure(call: Call<ResponseCartList>, t: Throwable) {
                view.onLoading(false)
            }
        })
    }

    override fun deleteItemCart(kd_keranjang: Long) {
        ApiService.endpoint.deleteItemCart(kd_keranjang).enqueue(object : Callback<ResponseCartUpdate> {

            override fun onResponse(call: Call<ResponseCartUpdate>, response: Response<ResponseCartUpdate>) {
                if (response.isSuccessful) response.body()?.let {
                    view.onResultDelete(it)
                }
            }

            override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun deleteCart(username: String) {
        ApiService.endpoint.deleteCart(username).enqueue(object : Callback<ResponseCartUpdate> {

            override fun onResponse(call: Call<ResponseCartUpdate>, response: Response<ResponseCartUpdate>) {
                if (response.isSuccessful) response.body()?.let {
                    view.onResultDelete(it)
                    view.showMessage(it.msg ?: "")
                }
            }

            override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {

            }
        })
    }
}