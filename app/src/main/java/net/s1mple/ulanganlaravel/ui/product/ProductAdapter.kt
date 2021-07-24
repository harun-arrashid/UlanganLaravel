package net.s1mple.ulanganlaravel.ui.product

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_product.view.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.Constant
import net.s1mple.ulanganlaravel.data.model.product.DataProduct
import net.s1mple.ulanganlaravel.utils.GlideHelper

class ProductAdapter(val product: MutableList<DataProduct>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(product[position])
    }

    override fun getItemCount(): Int = product.size

    inner class ProductViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(product: DataProduct) {
            view.txvName.text  = product.namaProduk
            view.txvPrice.text = product.hargaRupiah
            view.txvStock.text = "Stok Tersedia (${product.stok})"

            itemView.setOnClickListener {
                Constant.PRODUCT_ID = product.kdProduk?.toLong() ?: 0
                Constant.PRODUCT_NAME = product.namaProduk ?: ""
                Constant.IS_CHANGED = true

                (view.context as Activity).finish()
            }

            GlideHelper.setImage(view.context, product.gambarProduk ?: "", view.imvImage)
        }
    }

    fun setData(it: List<DataProduct>?) {
        product.clear()
        product.addAll(it!!)
        notifyDataSetChanged()
    }

}