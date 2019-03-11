package com.communistutopia.spacetrader.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.adapter.MarketplacePagerAdapter
import kotlinx.android.synthetic.main.marketplace_activity.*

/**
 * Activity that wraps the two fragments (buy and sell)
 */
class MarketplaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marketplace_activity)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Marketplace"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        // Set up the ViewPager (the tabs)
        val fragmentAdapter = MarketplacePagerAdapter(supportFragmentManager)
        pager.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(pager)
    }

    // Make back button work
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
