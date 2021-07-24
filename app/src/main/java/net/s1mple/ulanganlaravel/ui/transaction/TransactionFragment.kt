package net.s1mple.ulanganlaravel.ui.transaction

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_transaction.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.Constant
import net.s1mple.ulanganlaravel.data.database.PrefManager
import net.s1mple.ulanganlaravel.data.model.transaction.ResponseTransactionList
import net.s1mple.ulanganlaravel.ui.cart.CartActivity
import net.s1mple.ulanganlaravel.ui.transaction.detail.TransactionDetailFragment


class TransactionFragment : Fragment(), TransactionContract.View {

    private val pref by lazy { PrefManager(requireActivity()) }
    //private val presenter by lazy { TransactionPresenter(this) }

    private lateinit var presenter: TransactionPresenter
    private lateinit var mAdapter: TransactionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_transaction, container, false)
        presenter = TransactionPresenter(this)
        initListener(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Transaksi"
        presenter.getTransactionByUsername(pref.prefUsername)
    }

    override fun initFragment() {
        mAdapter = TransactionAdapter(requireActivity(), arrayListOf()) {
            dataTransaction, position ->
            onClickTransaction(dataTransaction.noFaktur ?: "")
        }
    }

    override fun initListener(view: View) {
        val rvTransaction = view.findViewById<RecyclerView>(R.id.rv_ransaction)
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe_transaction)
        val fab   = view.findViewById<FloatingActionButton>(R.id.fab_transaction)

        rvTransaction?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mAdapter
        }

        swipe.setOnRefreshListener {
            presenter.getTransactionByUsername(pref.prefUsername)
        }

        fab.setOnClickListener {
            activity?.startActivity(Intent(activity, CartActivity::class.java))
        }
    }

    override fun onLoading(it: Boolean) {
        swipe_transaction.isRefreshing = it
    }

    override fun onResult(it: ResponseTransactionList) {
        mAdapter.setData(it.data)
    }

    override fun onClickTransaction(invoice: String) {
        Constant.INVOICE = invoice
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, TransactionDetailFragment())
            ?.commit()
    }

}