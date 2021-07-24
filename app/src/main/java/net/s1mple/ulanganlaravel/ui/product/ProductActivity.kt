package net.s1mple.ulanganlaravel.ui.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_product.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.model.product.DataCategory
import net.s1mple.ulanganlaravel.data.model.product.ResponseCategory
import net.s1mple.ulanganlaravel.data.model.product.ResponseProduct

class ProductActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var presenter: ProductPresenter
    private var kdKategori: Long = 0 // untuk menampung data sementara

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        initActivity()
        initListener()
        presenter = ProductPresenter(this)
        presenter.getCategory()
    }

    override fun initActivity() {
        supportActionBar?.hide()

        productAdapter  = ProductAdapter(arrayListOf())
        categoryAdapter = CategoryAdapter(mutableListOf()) {
            category: DataCategory, position: Int ->
            kdKategori = category.kdKategori?.toLong() ?: 0
            presenter.getProduct(category.kdKategori!!.toLong())
        }

    }

    override fun initListener() {
        rcvCategory.apply {
            layoutManager = GridLayoutManager(applicationContext, 3)
            adapter = categoryAdapter
        }

        rcvProduct.apply {
            layoutManager = GridLayoutManager(this@ProductActivity, 3)
            adapter = productAdapter
        }

        swipe.setOnRefreshListener {
            when(View.VISIBLE) {
                rcvCategory.visibility -> presenter.getCategory()
                rcvProduct.visibility  -> presenter.getProduct(kdKategori)
            }
        }

        imvClose.setOnClickListener {
            when(View.VISIBLE) {
                rcvCategory.visibility -> finish()
                rcvProduct.visibility -> {
                    rcvProduct.visibility = View.GONE
                    rcvCategory.visibility = View.VISIBLE
                    txvCategory.text = "Pilih Kategori"
                }
            }
        }
    }

    override fun onLoading(it: Boolean) {
        if (it) {
            swipe.isRefreshing = true
            rcvCategory.visibility = View.GONE
            rcvProduct.visibility = View.GONE
        } else swipe.isRefreshing = false
    }

    override fun onResultCategory(it: ResponseCategory) {
        rcvCategory.visibility = View.VISIBLE
        categoryAdapter.setData(it.data)
        txvCategory.text = "Pilih Kategori gan"
    }

    override fun onResultProduct(it: ResponseProduct) {
        rcvProduct.visibility = View.VISIBLE
        productAdapter.setData(it.data)
        txvCategory.text = it.data?.get(0)?.kategori
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        // ketika tombol back diTekan
        when(View.VISIBLE) {
            rcvCategory.visibility -> finish() // klo rv ini yg lg muncul maka lansung finish
            rcvProduct.visibility  -> { // klo rv ini yg lg muncul maka...
                rcvProduct.visibility = View.GONE // dihilangkan dari tampilan
                rcvCategory.visibility = View.VISIBLE // di munculkan kembali
                txvCategory.text = "Pilih Kategori" // titlenya berubah (mungkin?)
            }
        }
    }
}