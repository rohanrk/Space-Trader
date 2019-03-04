package com.communistutopia.spacetrader.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.communistutopia.spacetrader.view.ui.marketplace.MarketplaceFragment

class MarketplaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marketplace_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MarketplaceFragment.newInstance())
                .commitNow()
        }
    }

}
