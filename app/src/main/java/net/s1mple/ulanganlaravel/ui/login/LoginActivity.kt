package net.s1mple.ulanganlaravel.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.ResponseLogin
import net.s1mple.ulanganlaravel.data.database.PrefManager
import net.s1mple.ulanganlaravel.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginPresenter
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter   = LoginPresenter(this)
        prefManager = PrefManager(this)
    }

    override fun initActivity() {
        supportActionBar?.title = "Masuk"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        btn_login_login.setOnClickListener {
            val username = edt_login_username.text.toString()
            val password = edt_login_password.text.toString()

            presenter.doLogin(username, password)
        }
    }

    override fun onLoading(it: Boolean) {
        when(it) {
            true -> {
                progress_login.visibility  = View.VISIBLE
                btn_login_login.visibility = View.GONE
            }
            false -> {
                progress_login.visibility  = View.GONE
                btn_login_login.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseLogin: ResponseLogin) {
        presenter.setPref(prefManager, responseLogin.pegawai!!)
        finish()
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}