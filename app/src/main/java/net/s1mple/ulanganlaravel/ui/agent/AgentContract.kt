package net.s1mple.ulanganlaravel.ui.agent

import net.s1mple.ulanganlaravel.data.model.agent.DataAgent
import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentList
import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentUpdate

interface AgentContract {

    interface Presenter {
        fun getAgent() // untuk request ke API nya
        fun deleteAgent(id: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingAgent(loading: Boolean)
        fun onResultAgent(response: ResponseAgentList)
        fun onResultDelete(it: ResponseAgentUpdate)
        fun showMessage(message: String)
        fun showDialog(dateAgent: DataAgent, position: Int)
        fun showDialogDetail(dataAgent: DataAgent)
    }
}