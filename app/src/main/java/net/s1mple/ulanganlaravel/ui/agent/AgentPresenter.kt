package net.s1mple.ulanganlaravel.ui.agent

import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentList
import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentUpdate
import net.s1mple.ulanganlaravel.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgentPresenter(val view: AgentContract.View): AgentContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoadingAgent(false)
    }

    override fun getAgent() {
        view.onLoadingAgent(true)
        ApiService.endpoint.getAgent().enqueue(object : Callback<ResponseAgentList> {
            override fun onResponse(call: Call<ResponseAgentList>, response: Response<ResponseAgentList>) {
                view.onLoadingAgent(false)
                if (response.isSuccessful) {
                    view.onResultAgent(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseAgentList>, t: Throwable) {
                view.onLoadingAgent(false)
                view.showMessage(t.message.toString())
            }
        })
    }

    override fun deleteAgent(id: Long) {
        view.onLoadingAgent(true)
        ApiService.endpoint.deleteAgent(id).enqueue(object : Callback<ResponseAgentUpdate> {

            override fun onResponse(call: Call<ResponseAgentUpdate>, response: Response<ResponseAgentUpdate>) {
                view.onLoadingAgent(false)
                if (response.isSuccessful) response.body()?.let {
                    view.onResultDelete(it)
                }
            }

            override fun onFailure(call: Call<ResponseAgentUpdate>, t: Throwable) {
                view.onLoadingAgent(false)
            }
        })
    }
}