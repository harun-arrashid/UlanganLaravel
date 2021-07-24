package net.s1mple.ulanganlaravel.ui.login

import net.s1mple.ulanganlaravel.data.DataLogin
import net.s1mple.ulanganlaravel.data.ResponseLogin
import net.s1mple.ulanganlaravel.data.database.PrefManager

interface LoginContract {

    interface Presenter{
        fun  doLogin(username: String, password: String)
        fun setPref(prefManager: PrefManager, dataLogin: DataLogin)
    }

    //  untuk Activity
    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(it: Boolean)
        fun onResult(responseLogin: ResponseLogin)
        fun showMessage(msg: String)
    }

}