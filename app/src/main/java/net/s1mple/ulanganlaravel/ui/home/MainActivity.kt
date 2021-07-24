package net.s1mple.ulanganlaravel.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.database.PrefManager
import net.s1mple.ulanganlaravel.ui.agent.AgentActivity
import net.s1mple.ulanganlaravel.ui.login.LoginActivity
import net.s1mple.ulanganlaravel.ui.transaction.TransactionActivity
import net.s1mple.ulanganlaravel.ui.transaction.TransactionFragment
import net.s1mple.ulanganlaravel.ui.user.UserActivity

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var prefManager: PrefManager
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefManager = PrefManager(this)
        presenter   = MainPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        when(prefManager.prefIsLogin) {
            true -> {
                crv_main_user.visibility  = View.VISIBLE
                btn_main_login.visibility = View.GONE
            }
            false -> {
                crv_main_user.visibility  = View.GONE
                btn_main_login.visibility = View.VISIBLE
            }
        }
    }

    override fun initListener() {

        crv_main_transaction.setOnClickListener {
            if (prefManager.prefIsLogin) {
                startActivity(Intent(this, TransactionActivity::class.java))
            } else showMessage("Login dlu gan")
        }

        crv_main_agen.setOnClickListener {
            if (prefManager.prefIsLogin) {
                val intent = Intent(this, AgentActivity::class.java)
                startActivity(intent)
            } else showMessage("Login dlu gan")
        }

        crv_main_user.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        btn_main_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}