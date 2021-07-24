package net.s1mple.ulanganlaravel.ui.transaction.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_transaction_detail.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.Constant
import net.s1mple.ulanganlaravel.data.model.transaction.ResponseTransactionDetail

class TransactionDetailFragment : Fragment(), TransactionDetailContract.View {

    private lateinit var mAdapter: TransactionDetailAdapter
    private lateinit var presenter: TransactionDetailPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_detail, container, false)
        initFragment()
        presenter = TransactionDetailPresenter(this)
        initListener(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Detail Transaksi"
        presenter.getTransactionByInvoice(Constant.INVOICE)
    }

    override fun initFragment() {
        mAdapter = TransactionDetailAdapter(requireActivity(), mutableListOf())
    }

    override fun initListener(view: View) {
        val tvInvoice = view.findViewById<TextView>(R.id.txvInvoice)
        val rvDetail = view.findViewById<RecyclerView>(R.id.rcvDetail)
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)

        tvInvoice.text= Constant.INVOICE
        rvDetail?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mAdapter
        }

        swipe.setOnRefreshListener {
            presenter.getTransactionByInvoice(Constant.INVOICE)
        }
    }

    override fun onLoading(loading: Boolean) {
        swipe.isRefreshing = loading // ini tuh kaya Visibilitynya
    }

    override fun onResult(it: ResponseTransactionDetail) {
        mAdapter.setData(it.data)
    }


}