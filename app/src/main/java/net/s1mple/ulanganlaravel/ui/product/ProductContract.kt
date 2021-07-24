package net.s1mple.ulanganlaravel.ui.product

import net.s1mple.ulanganlaravel.data.model.product.ResponseCategory
import net.s1mple.ulanganlaravel.data.model.product.ResponseProduct

interface ProductContract {

    interface Presenter {
        fun getCategory()
        fun getProduct(kd_kategori: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(it: Boolean)
        fun onResultCategory(it: ResponseCategory)
        fun onResultProduct(it: ResponseProduct)
    }

}