package net.s1mple.ulanganlaravel.ui.agent.update

import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentDetail
import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentUpdate
import java.io.File

interface AgentUpdateContract {

    interface Presenter {
        fun getDetail(kd_agen: Long)
        fun updateAgent(kd_agen: Long, nama_toko: String, nama_pemilik: String, alamat: String, latitude: String,
                        longitude: String, gambar_toko: File?)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultDetail(it: ResponseAgentDetail)
        fun onResultUpdate(it: ResponseAgentUpdate)
        fun showMessage(it: String)
    }
}