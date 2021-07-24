package net.s1mple.ulanganlaravel.ui.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cart.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.database.PrefManager
import net.s1mple.ulanganlaravel.data.model.cart.DataCart
import net.s1mple.ulanganlaravel.data.model.cart.ResponseCartList
import net.s1mple.ulanganlaravel.data.model.cart.ResponseCartUpdate
import net.s1mple.ulanganlaravel.ui.agent.search.AgentSearchActivity
import net.s1mple.ulanganlaravel.ui.cart.add.CartAddActivity

class CartActivity : AppCompatActivity(), CartContract.View {

    private val pref by lazy { PrefManager(this) }

    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartPresenter: CartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartPresenter = CartPresenter(this)
        cartPresenter.getCart(pref.prefUsername)
    }

    override fun initActivity() {
        supportActionBar?.title = "Keranjang"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        cartAdapter = CartAdapter(arrayListOf()) {
            dataCart: DataCart, position: Int ->
            cartPresenter.deleteItemCart(dataCart.kd_keranjang!!)
        }
    }

    override fun initListener() {
        txvReset.visibility = View.GONE
        edtAgent.visibility = View.GONE

        rcvCart.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }

        swipe.setOnRefreshListener {
            cartPresenter.getCart(pref.prefUsername)
        }

        btnAdd.setOnClickListener {
            startActivity(Intent(this, CartAddActivity::class.java))
        }

        txvReset.setOnClickListener {
            showDialog()
        }

        edtAgent.setOnClickListener {
            startActivity(Intent(this, AgentSearchActivity::class.java))
        }

        btnCheckout.setOnClickListener {

        }
    }

    override fun onLoading(it: Boolean) {
        swipe.isRefreshing = it
    }

    override fun onResultCart(it: ResponseCartList) {
        if (it.dataCart.isNullOrEmpty()) {
            txvReset.visibility = View.GONE
            edtAgent.visibility = View.GONE
        } else {
            cartAdapter.setData(it.dataCart)
            txvReset.visibility = View.VISIBLE
            edtAgent.visibility = View.VISIBLE
        }
    }

    override fun showMessage(it: String) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    override fun onResultDelete(it: ResponseCartUpdate) {
        cartPresenter.getCart(pref.prefUsername)
        cartAdapter.removeAll()
    }

    override fun showDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin hapus semua data keranjang ?")
            setPositiveButton("Hapus") { dialog, _ ->
                cartPresenter.deleteCart(pref.prefUsername)
                dialog.dismiss()
            }
            setNegativeButton("Batal") { dialog, _ ->

            }
        }
        dialog.show()
    }
}