package com.communistutopia.spacetrader.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.adapter.MarketplacePagerAdapter
import com.communistutopia.spacetrader.viewmodel.MarketplaceViewModel
import kotlinx.android.synthetic.main.marketplace_activity.*

/**
 * Activity that wraps the two fragments (buy and sell)
 *
 * @author Drake Witt
 */
class MarketplaceActivity : AppCompatActivity() {

    lateinit var viewModel: MarketplaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marketplace_activity)

        // Configure Viewmodel
        viewModel = ViewModelProviders.of(this).get(MarketplaceViewModel::class.java)
        viewModel.market = intent.getParcelableExtra("market")
        viewModel.player = intent.getParcelableExtra("player")
        viewModel.playerObservable.value = viewModel.player
        viewModel.marketObservable.value = viewModel.market
        viewModel.initializeInventory() // Should always be called after market is set

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
