package net.s1mple.ulanganlaravel.ui.product

import net.s1mple.ulanganlaravel.data.model.product.ResponseCategory
import net.s1mple.ulanganlaravel.data.model.product.ResponseProduct
import net.s1mple.ulanganlaravel.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPresenter(val view: ProductContract.View): ProductContract.Presenter {

    override fun getCategory() {
        view.onLoading(true)
        ApiService.endpoint.getCategory().enqueue(object : Callback<ResponseCategory> {

            override fun onResponse(call: Call<ResponseCategory>, response: Response<ResponseCategory>) {
                view.onLoading(false)
                if (response.isSuccessful) response.body()?.let {
                    view.onResultCategory(it)
                }
            }

            override fun onFailure(call: Call<ResponseCategory>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun getProduct(kd_kategori: Long) {
        view.onLoading(true)
        ApiService.endpoint.getProductByCategory(kd_kategori).enqueue(object : Callback<ResponseProduct> {

            override fun onResponse(call: Call<ResponseProduct>, response: Response<ResponseProduct>) {
                view.onLoading(false)
                if (response.isSuccessful) response.body()?.let {
                    view.onResultProduct(it)
                }
            }

            override fun onFailure(call: Call<ResponseProduct>, t: Throwable) {
                view.onLoading(false)
            }
        })
    }
}