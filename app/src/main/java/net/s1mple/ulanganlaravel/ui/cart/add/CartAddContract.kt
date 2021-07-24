package net.s1mple.ulanganlaravel.ui.cart.add

import net.s1mple.ulanganlaravel.data.model.cart.ResponseCartUpdate

interface CartAddContract {

    interface Presenter {
        fun addCart(username: String, kd_produk: Long, jumlah: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(it: Boolean)
        fun onResult(it: ResponseCartUpdate)
        fun showMessage(it: String)
    }


}