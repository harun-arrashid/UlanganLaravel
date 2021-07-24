package net.s1mple.ulanganlaravel.ui.agent.search

import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentList

interface AgentSearchContract {

    interface Presenter {
        fun getAgent()
        fun searchAgent(keyword: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingAgent(it: Boolean)
        fun onResultAgent(it: ResponseAgentList)
    }

}