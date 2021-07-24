package net.s1mple.ulanganlaravel.ui.transaction.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_transaction_detail.view.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.model.transaction.DataDetail
import net.s1mple.ulanganlaravel.utils.GlideHelper
import java.text.NumberFormat
import java.util.*

class TransactionDetailAdapter(val context: Context, var detail: MutableList<DataDetail>)
    : RecyclerView.Adapter<TransactionDetailAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_transaction_detail, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(detail[position])
    }

    override fun getItemCount(): Int = detail.size

    inner class TransactionViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(detail: DataDetail) {
            view.txvCategory.text = detail.kategori
            view.txvNameProduct.text = detail.namaProduk
            view.txvPrice.text = "${detail.hargaRupiah} x ${detail.jumlah}"

            GlideHelper.setImage(context, detail.gambarProduk ?: "", view.imvImage)

            val total: Double = detail.jumlah?.toDouble()?.times(detail.harga?.toDouble() ?: 0.0) ?: 0.0
            val totalRupiah = NumberFormat.getNumberInstance(Locale.GERMANY).format(total)

            view.txvTotal.text = "Rp $totalRupiah"
        }
    }

    fun setData(data: List<DataDetail>?) {
        detail.clear()
        data?.let { detail.addAll(it) }
        notifyDataSetChanged()
    }
}

