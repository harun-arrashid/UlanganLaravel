package net.s1mple.ulanganlaravel.ui.transaction

import net.s1mple.ulanganlaravel.data.model.transaction.ResponseTransactionList

interface TransactionContract {

    interface Presenter {
        fun getTransactionByUsername(username: String)
    }

    interface View {
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(it: Boolean)
        fun onResult(it: ResponseTransactionList)
        fun onClickTransaction(invoice: String)
    }

}