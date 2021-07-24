package net.s1mple.ulanganlaravel.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_cart.view.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.model.cart.DataCart
import net.s1mple.ulanganlaravel.utils.GlideHelper
import java.text.NumberFormat
import java.util.*

class CartAdapter(private val cart: MutableList<DataCart>, val clickListener: (DataCart, Int) -> Unit)
    : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cart[position])
    }

    override fun getItemCount(): Int = cart.size

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(cart: DataCart) {
            view.txvCategory.text = cart.kategori
            view.txvNameProduct.text = cart.nama_produk
            view.txvPrice.text = "${cart.harga_rupiah} x ${cart.jumlah}"

            val total: Double = cart.jumlah?.toDouble()?.times(cart.harga?.toDouble() ?: 0.0) ?: 0.0
            val totalRupiah: String = NumberFormat.getNumberInstance(Locale.GERMANY).format(total)
            view.txvTotal.text = "Rp $totalRupiah"

            GlideHelper.setImage(view.context, cart.gambar_produk ?: "", view.imvImage)
            view.imvDelete.setOnClickListener {
                clickListener(cart, adapterPosition)
                 //removeCart(adapterPosition)
            }
        }
    }

    fun setData(it: List<DataCart>?) {
        cart.clear()
        cart.addAll(it!!)
        notifyDataSetChanged()
    }

    fun removeCart(position: Int) {
        cart.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cart.size)
    }

    fun removeAll() {
        cart.clear()
        notifyDataSetChanged()
    }
}