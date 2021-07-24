package net.s1mple.ulanganlaravel.ui.cart.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cart_add.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.Constant
import net.s1mple.ulanganlaravel.data.database.PrefManager
import net.s1mple.ulanganlaravel.data.model.cart.ResponseCartUpdate
import net.s1mple.ulanganlaravel.ui.product.ProductActivity

class CartAddActivity : AppCompatActivity(), CartAddContract.View {

    private val pref by lazy { PrefManager(this) }
    private lateinit var presenter: CartAddPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_add)

        presenter = CartAddPresenter(this)
        initActivity()
        initListener()
        onLoading(false)
    }

    override fun onStart() {
        super.onStart()
        if (Constant.IS_CHANGED) {
            Constant.IS_CHANGED = false
            edtProduct.setText(Constant.PRODUCT_NAME)
            txvQty.visibility = View.VISIBLE
            npQuantity.visibility = View.VISIBLE
        } else {
            txvQty.visibility = View.GONE
            npQuantity.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.PRODUCT_ID = 0
        Constant.PRODUCT_NAME = ""
        Constant.PRODUCT_QT = 0
        Constant.IS_CHANGED = false
    }

    override fun initActivity() {
        supportActionBar?.title = "Tambah Produk"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txvQty.visibility = View.GONE // awalnya di GONE dlu soalnya kn blm ada data tambah produknya
        npQuantity.visibility = View.GONE
    }

    override fun initListener() {
        edtProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }

        npQuantity.minValue = 1
        npQuantity.maxValue = 10
        npQuantity.wrapSelectorWheel = false
        npQuantity.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            Constant.PRODUCT_QT = newVal.toLong()
        }

        btnSubmit.setOnClickListener {
            if (Constant.PRODUCT_ID > 0) {
                Constant.PRODUCT_QT = if (Constant.PRODUCT_QT > 0) Constant.PRODUCT_QT else 1

                presenter.addCart(pref.prefUsername, Constant.PRODUCT_ID, Constant.PRODUCT_QT)
            } else edtProduct.error = "Tidak boleh kosong"
        }
    }

    override fun onLoading(it: Boolean) {
        if (it) {
            progress.visibility = View.VISIBLE
            btnSubmit.visibility = View.GONE
        } else {
            progress.visibility = View.GONE
            btnSubmit.visibility = View.VISIBLE
        }
    }

    override fun onResult(it: ResponseCartUpdate) {
        if (it.status == true) {
            Constant.IS_CHANGED = true
            finish()
        }
    }

    override fun showMessage(it: String) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}