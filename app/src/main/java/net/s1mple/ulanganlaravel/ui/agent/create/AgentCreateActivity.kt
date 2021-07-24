package net.s1mple.ulanganlaravel.ui.agent.create

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lazday.poslaravel.util.GalleryHelper
import kotlinx.android.synthetic.main.activity_agent_create.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.Constant
import net.s1mple.ulanganlaravel.data.model.agent.ResponseAgentUpdate
import net.s1mple.ulanganlaravel.ui.agent.AgentMapsActivity
import net.s1mple.ulanganlaravel.utils.FileUtils

class AgentCreateActivity : AppCompatActivity(), AgentCreateContract.View {

    private var uriImage: Uri? = null
    private var pickImage = 1 // buat RequestCode
    lateinit var presenter: AgentCreatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_create)

        presenter = AgentCreatePresenter(this)

    }

    override fun initActivity() {
        supportActionBar?.title = "Agent Baru"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun initListener() {
        edt_agent_location.setOnClickListener {
            val intent = Intent(this, AgentMapsActivity::class.java)
            startActivity(intent)
        }

        img_agent_insert.setOnClickListener {
            if (GalleryHelper.permissionGallery(this, this, pickImage)) {
                GalleryHelper.openGallery(this)
            }
        }

        btnagent_submit.setOnClickListener {
            val nameStore = edt_agent_nameStore.text
            val nameOwner = edt_agent_nameOwner.text
            val address   = edt_agent_address.text
            val location  = edt_agent_location.text

            if (nameStore.isNullOrEmpty() || nameOwner.isNullOrEmpty() || address.isNullOrEmpty() || location.isNullOrEmpty() || uriImage == null) {
                showMessage("Data Tidak Boleh Kosong")
            } else {
                presenter.insertAgent(nameStore.toString(), nameOwner.toString(), address.toString(),
                    Constant.LATITUDE, Constant.LONGITUDE, FileUtils.getFile(this, uriImage))
            }
        }
    }

//    private fun initListener() {

//    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> {
                progress_agent.visibility = View.VISIBLE
                btnagent_submit.visibility = View.GONE
            }
            false -> {
                progress_agent.visibility = View.GONE
                btnagent_submit.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseAgentUpdate: ResponseAgentUpdate) {
        showMessage(responseAgentUpdate.msg ?: "")
        this.finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImage && resultCode == Activity.RESULT_OK) {
            uriImage = data?.data
            img_agent_insert.setImageURI(uriImage)
        }
    }

    override fun onStart() {
        super.onStart()
        if (Constant.LATITUDE.isNotEmpty()) {
            edt_agent_location.setText("${Constant.LATITUDE}, ${Constant.LONGITUDE}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.LATITUDE  = ""
        Constant.LONGITUDE = ""
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}