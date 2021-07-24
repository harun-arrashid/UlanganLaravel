package net.s1mple.ulanganlaravel.ui.transaction.detail

import net.s1mple.ulanganlaravel.data.model.transaction.ResponseTransactionDetail

interface TransactionDetailContract {

    interface Presenter {
        fun getTransactionByInvoice(it: String)
    }

    interface View {
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onResult(it: ResponseTransactionDetail)
    }

}