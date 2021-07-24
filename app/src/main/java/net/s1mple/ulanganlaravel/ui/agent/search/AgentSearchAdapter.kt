package net.s1mple.ulanganlaravel.ui.agent.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_agent_search.view.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.Constant
import net.s1mple.ulanganlaravel.data.model.agent.DataAgent
import net.s1mple.ulanganlaravel.utils.GlideHelper

class AgentSearchAdapter(val dataAgent: MutableList<DataAgent>, val clickListener: (DataAgent) -> Unit)
    : RecyclerView.Adapter<AgentSearchAdapter.AgentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_agent_search, parent, false)
        return AgentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        holder.bind(dataAgent[position])

    }

    override fun getItemCount(): Int = dataAgent.size

    inner class AgentViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(dataAgent: DataAgent) {
            view.txvNameStore.text = dataAgent.namaToko
            view.txvLocation.text   = dataAgent.alamat

            GlideHelper.setImage(view.context, dataAgent.gambarToko ?: "", view.imvImage)

            itemView.setOnClickListener {
                Constant.AGENT_ID = dataAgent.kdAgen ?: 0
                clickListener(dataAgent)
            }
        }
    }

    fun setData(it: List<DataAgent>) {
        dataAgent.clear()
        dataAgent.addAll(it)
        notifyDataSetChanged()
    }
}
