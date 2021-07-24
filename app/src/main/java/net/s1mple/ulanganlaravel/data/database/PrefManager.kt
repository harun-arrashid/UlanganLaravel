package net.s1mple.ulanganlaravel.data.database

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.intPref
import hu.autsoft.krate.stringPref

class PrefManager(context: Context): Krate {

    // Variabel untuk Key2nya
    private val PREF_IS_LOGIN = "pref_is_login"
    private val PREF_USERNAME = "pref_username"
    private val PREF_PASSWORD = "pref_password"
    private val PREF_NAMA     = "pref_nama_pegawai"
    private val PREF_JK       = "pref_jk"
    private val PREF_ALAMAT   = "pref_alamat"
    private val PREF_IS_AKTIF = "pref_is_aktif"

    override val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences(
            "shared_pref_gan", Context.MODE_PRIVATE
        )
    }


    var prefIsLogin by booleanPref(PREF_IS_LOGIN, false)
    var prefUsername by stringPref(PREF_USERNAME, "")
    var prefPassword by stringPref(PREF_PASSWORD, "")
    var prefNamaPegawai by stringPref(PREF_NAMA, "")
    var prefJk by stringPref(PREF_JK, "")
    var prefAlamat by stringPref(PREF_ALAMAT, "")
    var prefIsAktif by intPref(PREF_IS_AKTIF, 0)

    // untuk Logout SharefPref
    fun logout() {
        sharedPreferences.edit().clear().apply()
    }

}