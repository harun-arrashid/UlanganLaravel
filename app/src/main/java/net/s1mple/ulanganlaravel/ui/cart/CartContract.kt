package net.s1mple.ulanganlaravel.ui.cart

import net.s1mple.ulanganlaravel.data.model.cart.ResponseCartList
import net.s1mple.ulanganlaravel.data.model.cart.ResponseCartUpdate

interface CartContract {

    interface Presenter {
        fun getCart(username: String)

        fun deleteItemCart(kd_keranjang: Long)
        fun deleteCart(username: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(it: Boolean)
        fun onResultCart(it: ResponseCartList)
        fun showMessage(it: String)

        fun onResultDelete(it: ResponseCartUpdate)
        fun showDialog()
    }

}