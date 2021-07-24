package net.s1mple.ulanganlaravel.ui.user

import net.s1mple.ulanganlaravel.data.database.PrefManager

class UserPresenter(private val view: UserContract.View): UserContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun doLogin(prefManager: PrefManager) {
        if (prefManager.prefIsLogin) view.onResultLogin(prefManager) // klo udh login brarti nampilin data yg ada di sharedPref
    }

    override fun doLogout(prefManager: PrefManager) {
        prefManager.logout()
        view.showMessage("Berhasil Logout")
        view.onResultLogout()
    }
}