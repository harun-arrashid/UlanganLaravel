package net.s1mple.ulanganlaravel.ui.transaction.detail

import net.s1mple.ulanganlaravel.data.model.transaction.ResponseTransactionDetail
import net.s1mple.ulanganlaravel.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionDetailPresenter(val view: TransactionDetailContract.View): TransactionDetailContract.Presenter {

//    init {
//        view.initFragment()
//    }

    override fun getTransactionByInvoice(it: String) {
        view.onLoading(true)
        ApiService.endpoint.getTransactionByInvoice(it).enqueue(object : Callback<ResponseTransactionDetail> {

            override fun onResponse(call: Call<ResponseTransactionDetail>, response: Response<ResponseTransactionDetail>) {
                view.onLoading(false)
                if (response.isSuccessful) response.body()?.let {
                    view.onResult(it)
                }
            }

            override fun onFailure(call: Call<ResponseTransactionDetail>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

}