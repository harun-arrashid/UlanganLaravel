package net.s1mple.ulanganlaravel.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user.*
import net.s1mple.ulanganlaravel.R
import net.s1mple.ulanganlaravel.data.database.PrefManager

class UserActivity : AppCompatActivity(), UserContract.View {

    private lateinit var prefManager: PrefManager
    private lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        prefManager = PrefManager(this)
        presenter = UserPresenter(this)
        presenter.doLogin(prefManager)
    }

    override fun initActivity() {
        supportActionBar?.hide()
    }

    override fun initListener() {
        btn_user_back.setOnClickListener {
            finish()
        }

        tv_user_logout.setOnClickListener {
            presenter.doLogout(prefManager)
        }
    }

    override fun onResultLogin(prefManager: PrefManager) {
        tv_user_username.text = prefManager.prefUsername
        tv_user_name.text     = prefManager.prefNamaPegawai
        tv_user_address.text  = prefManager.prefAlamat
        tv_user_gender.text   = prefManager.prefJk
    }

    override fun onResultLogout() {
        TODO("Not yet implemented")
    }

    override fun showMessage(msg: String) {
        TODO("Not yet implemented")
    }
}