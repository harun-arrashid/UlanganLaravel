package net.s1mple.ulanganlaravel.ui.transaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.s1mple.ulanganlaravel.R

class TransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initActivity()
    }

    private fun initActivity() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, TransactionFragment(), "tag_fragment")
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        if(supportFragmentManager.findFragmentByTag("tag_fragment") == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TransactionFragment(), "tag_fragment")
                .commit()
        } else finish()
        return super.onSupportNavigateUp()
    }
}