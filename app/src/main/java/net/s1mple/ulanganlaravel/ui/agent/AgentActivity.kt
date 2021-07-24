package net.s1mple.ulanganlaravel.ui.agent

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_agent.*
import kotlinx.android.synthetic.main.content_agent.*
import kotlinx.android.synthetic.main.dialog_agent.view.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.Constant
import net.s1mple.ulanganlaravel.data.model.agent.DataAgent
import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentList
import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentUpdate
import net.s1mple.ulanganlaravel.ui.agent.create.AgentCreateActivity
import net.s1mple.ulanganlaravel.ui.agent.update.AgentUpdateActivity
import net.s1mple.ulanganlaravel.utils.GlideHelper
import net.s1mple.ulanganlaravel.utils.MapsHelper

class AgentActivity : AppCompatActivity(), AgentContract.View, OnMapReadyCallback {

    private lateinit var presenter: AgentPresenter
    private lateinit var agentAdapter: AgentAdapter
    private lateinit var agent: DataAgent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent)
        setSupportActionBar(findViewById(R.id.toolbar))

        presenter = AgentPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getAgent()
    }

    override fun initActivity() {
        supportActionBar?.title = "Agent"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        MapsHelper.permissionMap(this, this)
    }

    override fun initListener() {
        agentAdapter = AgentAdapter(this, arrayListOf()) {
                dataAgent: DataAgent, position: Int, type: String ->

            agent = dataAgent

            when (type) {
                "update" -> startActivity(Intent(this, AgentUpdateActivity::class.java))
                "delete" -> showDialog(dataAgent, position)
                "detail" -> showDialogDetail(dataAgent)
            }
        }
        rv_agent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = agentAdapter
            setHasFixedSize(true)
        }

        swipe_agent?.setOnRefreshListener {
            presenter.getAgent()
        }

        fab_agent.setOnClickListener {
            val intent = Intent(this, AgentCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onLoadingAgent(loading: Boolean) {
        when(loading) {
            true -> swipe_agent.isRefreshing  = true
            false -> swipe_agent.isRefreshing = false
        }
    }

    override fun onResultAgent(response: ResponseAgentList) {
        val dataAgent: List<DataAgent>? = response.data
        agentAdapter.setData(dataAgent!!)
    }

    override fun onResultDelete(it: ResponseAgentUpdate) {
        showMessage(it.msg ?: "")
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }

    override fun showDialog(dateAgent: DataAgent, position: Int) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin ingin menghapus ${agent.namaToko}")

            setPositiveButton("Hapus") { dialog, _ ->
                presenter.deleteAgent(Constant.AGENT_ID.toLong())
                agentAdapter.removeAgent(position) // jd positionnya cmn buat hapus lwt adapter aja
                dialog.dismiss()
            }

            setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    override fun showDialogDetail(dataAgent: DataAgent) {
        val dialog = BottomSheetDialog(this)
        val view   = layoutInflater.inflate(R.layout.dialog_agent, null)

        GlideHelper.setImage(this, dataAgent.gambarToko ?: "", view.imvStore)
        view.txvNameStore.text = dataAgent.namaToko
        view.txvName.text      = dataAgent.namaPemilik
        view.txvAddress.text   = dataAgent.alamat

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        view.imvClose.setOnClickListener {
            supportFragmentManager.beginTransaction().remove(mapFragment).commit()
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val latLng = LatLng(agent.latitude?.toDouble() ?: 0.0, agent.longitude?.toDouble() ?: 0.0)
        googleMap?.addMarker(MarkerOptions().position(latLng).title(agent.namaToko))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
    }
}