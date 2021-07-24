package net.s1mple.ulanganlaravel.ui.agent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_agent.view.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.Constant
import net.s1mple.ulanganlaravel.data.model.agent.DataAgent
import net.s1mple.ulanganlaravel.utils.GlideHelper

class AgentAdapter(val context: Context, var dataAgent: ArrayList<DataAgent>,
                val clickListener: (DataAgent, Int, String) -> Unit): RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_agent, parent, false)
        return AgentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        holder.bind(dataAgent[position])
    }

    override fun getItemCount(): Int = dataAgent.size

    inner class AgentViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(dataAgent: DataAgent) {
            view.tv_list_name_store.text = dataAgent.namaToko
            view.tv_list_location.text = dataAgent.alamat

            GlideHelper.setImage(context, dataAgent?.gambarToko ?: "", view.img_list_agent)

            itemView.setOnClickListener {
                Constant.AGENT_ID = dataAgent.kdAgen ?: 0
                clickListener(dataAgent, adapterPosition, "detail")
            }

            view.tv_list_options.setOnClickListener {
                val popupMenu = PopupMenu(context, view.tv_list_options)
                popupMenu.inflate(R.menu.menu_options)
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.action_update -> {
                            Constant.AGENT_ID =  dataAgent.kdAgen!!
                            clickListener(dataAgent, adapterPosition, "update")
                        }

                        R.id.action_delete -> {
                            Constant.AGENT_ID =  dataAgent.kdAgen!!
                            clickListener(dataAgent, adapterPosition, "delete")
                        }
                    }
                    true
                }
                popupMenu.show()
            }
        }
    }

    fun setData(newDataAgent: List<DataAgent>) {
        dataAgent.clear()
        dataAgent.addAll(newDataAgent)
        notifyDataSetChanged()
    }

    fun removeAgent(position: Int) {
        dataAgent.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataAgent.size)
    }
}