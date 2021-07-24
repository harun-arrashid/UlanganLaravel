package net.s1mple.ulanganlaravel.ui.home

interface MainContract {

    interface View {
        fun initListener()
        fun showMessage(msg: String)
    }
}