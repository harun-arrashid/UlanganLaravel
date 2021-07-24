package net.s1mple.ulanganlaravel.ui.agent.search

import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentList
import net.s1mple.ulanganlaravel.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgentSearchPresenter(val view: AgentSearchContract.View): AgentSearchContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getAgent() {
        view.onLoadingAgent(true)
        ApiService.endpoint.getAgent().enqueue(object : Callback<ResponseAgentList> {

            override fun onResponse(call: Call<ResponseAgentList>, response: Response<ResponseAgentList>) {
                view.onLoadingAgent(false)
                if (response.isSuccessful) response.body()?.let {
                    view.onResultAgent(it)
                }
            }

            override fun onFailure(call: Call<ResponseAgentList>, t: Throwable) {
                view.onLoadingAgent(false)
            }

        })
    }

    override fun searchAgent(keyword: String) {
        view.onLoadingAgent(true)
        ApiService.endpoint.searchAgent(keyword).enqueue(object : Callback<ResponseAgentList> {

            override fun onResponse(call: Call<ResponseAgentList>, response: Response<ResponseAgentList>) {
                view.onLoadingAgent(false)
                if (response.isSuccessful) response.body()?.let {
                    view.onResultAgent(it)
                }
            }

            override fun onFailure(call: Call<ResponseAgentList>, t: Throwable) {
                view.onLoadingAgent(false)
            }
        })
    }
}