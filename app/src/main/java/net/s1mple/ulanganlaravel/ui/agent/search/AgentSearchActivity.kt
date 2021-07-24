package net.s1mple.ulanganlaravel.ui.agent.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_agent_search.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.Constant
import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentList

class AgentSearchActivity : AppCompatActivity(), AgentSearchContract.View {

    private lateinit var presenter: AgentSearchPresenter
    private lateinit var mAdapter: AgentSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_search)
        
        presenter = AgentSearchPresenter(this)
        presenter.getAgent()
    }

    override fun initActivity() {
        supportActionBar?.title = "Pilih Agent"
    }

    override fun initListener() {
        mAdapter = AgentSearchAdapter(mutableListOf()) {
            dataAgent->
            Constant.AGENT_ID = dataAgent.kdAgen ?: 0
            Constant.AGENT_NAME = dataAgent.namaToko ?: ""
            Constant.IS_CHANGED = true
            finish()
        }

        edtSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.searchAgent(edtSearch.text.toString())
                true
            } else false
        }

        rcvAgent.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mAdapter
        }

        swipe_search.setOnRefreshListener {
            presenter.getAgent()
        }
    }

    override fun onLoadingAgent(it: Boolean) {
        swipe_search.isRefreshing = it
    }

    fun showToast1(it: String) {
            //// asdasdasdasd
    }

    fun showkk(it: String) {

    }

    override fun onResultAgent(it: ResponseAgentList) {
        mAdapter.setData(it.data!!)
    }
}