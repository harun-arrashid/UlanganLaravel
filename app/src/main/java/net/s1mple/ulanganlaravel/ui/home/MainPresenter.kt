package net.s1mple.ulanganlaravel.ui.home

class MainPresenter(view: MainContract.View) {
    init {
        view.initListener()
    }
}