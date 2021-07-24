package net.s1mple.ulanganlaravel.ui.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_transaction.view.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.model.transaction.DataTransaction

class TransactionAdapter(val context: Context, var transaction: MutableList<DataTransaction>,
                      val clickListener: (DataTransaction, Int) -> Unit): RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transaction[position])

        holder.view.txvSee.setOnClickListener {
            clickListener(transaction[position], position)
        }
    }

    override fun getItemCount(): Int = transaction.size

    inner class TransactionViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(it: DataTransaction) {
            view.txvInvoice.text = it.noFaktur
            view.txvDate.text    = it.tglPenjualan
            view.txvTotal.text   = it.totalRupiah
        }
    }

    fun setData(it: List<DataTransaction>?) {
        transaction.clear()
        transaction.addAll(it!!)
        notifyDataSetChanged()
    }
}