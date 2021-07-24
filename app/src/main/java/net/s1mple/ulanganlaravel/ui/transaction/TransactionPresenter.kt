package net.s1mple.ulanganlaravel.ui.transaction

import net.s1mple.ulanganlaravel.data.model.transaction.ResponseTransactionList
import net.s1mple.ulanganlaravel.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionPresenter(val view: TransactionContract.View): TransactionContract.Presenter {

    init {
        view.initFragment()
    }

    override fun getTransactionByUsername(username: String) {
        view.onLoading(true)
        ApiService.endpoint.getTransactionByUsername(username).enqueue(object : Callback<ResponseTransactionList> {

            override fun onResponse(call: Call<ResponseTransactionList>, response: Response<ResponseTransactionList>) {
                view.onLoading(false)
                if (response.isSuccessful) response.body()?.let {
                    view.onResult(it)
                }
            }

            override fun onFailure(call: Call<ResponseTransactionList>, t: Throwable) {
                view.onLoading(false)
            }
        })
    }
}