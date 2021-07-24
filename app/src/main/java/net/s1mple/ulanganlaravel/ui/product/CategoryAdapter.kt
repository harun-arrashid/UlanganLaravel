package net.s1mple.ulanganlaravel.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_category.view.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.Constant
import net.s1mple.ulanganlaravel.data.model.product.DataCategory
import net.s1mple.ulanganlaravel.utils.GlideHelper

class CategoryAdapter(val category: MutableList<DataCategory>,val clickListener: (DataCategory, Int) -> Unit)
    : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(category[position])
    }

    override fun getItemCount(): Int = category.size

    inner class CategoryViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(data: DataCategory) {
            view.txvCategory.text = data.kategori

            itemView.setOnClickListener {
                Constant.CATEGORY_ID = data.kdKategori?.toLong() ?: 0
                clickListener(data, adapterPosition)
            }

            GlideHelper.setImage(view.context, data.gambarKategori ?: "", view.imvImage)
        }
    }

    fun setData(it: List<DataCategory>?) {
        category.clear()
        category.addAll(it!!)
        notifyDataSetChanged()
    }
}