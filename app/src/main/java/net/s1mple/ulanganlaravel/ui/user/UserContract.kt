package net.s1mple.ulanganlaravel.ui.user

import net.s1mple.ulanganlaravel.data.database.PrefManager

interface UserContract {

    interface Presenter {
        fun doLogin(prefManager: PrefManager)
        fun doLogout(prefManager: PrefManager)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onResultLogin(prefManager: PrefManager)
        fun onResultLogout()
        fun showMessage(msg: String)
    }

}