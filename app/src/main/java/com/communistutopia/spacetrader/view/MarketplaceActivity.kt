package com.communistutopia.spacetrader.view

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.view.ui.marketplace.MarketplaceSellFragment
import android.support.v4.view.ViewPager
import com.communistutopia.spacetrader.adapter.MarketplacePagerAdapter
import kotlinx.android.synthetic.main.marketplace_activity.*

class MarketplaceActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marketplace_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MarketplaceSellFragment.newInstance())
                .commitNow()
        }

        val fragmentAdapter = MarketplacePagerAdapter(supportFragmentManager)
        pager.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(pager)

        // viewPager = findViewById(R.id.pager)
    }

}
