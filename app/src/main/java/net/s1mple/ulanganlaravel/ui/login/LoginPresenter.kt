package net.s1mple.ulanganlaravel.ui.login

import net.s1mple.ulanganlaravel.data.DataLogin
import net.s1mple.ulanganlaravel.data.ResponseLogin
import net.s1mple.ulanganlaravel.data.database.PrefManager
import net.s1mple.ulanganlaravel.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val view: LoginContract.View): LoginContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun doLogin(username: String, password: String) {
        view.onLoading(true)

        ApiService.endpoint.loginUser(username, password).enqueue(object : Callback<ResponseLogin> {

            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    view.showMessage(response.body()?.msg ?: "")

                    if (response.body()?.status == true) view.onResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                view.showMessage(t.message.toString())
            }
        })
    }

    override fun setPref(prefManager: PrefManager, dataLogin: DataLogin) {
        prefManager.prefIsLogin     = true // tandanya dh login
        prefManager.prefUsername    = dataLogin.username ?: ""
        prefManager.prefNamaPegawai = dataLogin.namaPegawai ?: ""
        prefManager.prefJk          = dataLogin.jk ?: ""
        prefManager.prefAlamat      = dataLogin.alamat ?: ""
        prefManager.prefIsAktif     = dataLogin.isAktif ?: 0
    }
}