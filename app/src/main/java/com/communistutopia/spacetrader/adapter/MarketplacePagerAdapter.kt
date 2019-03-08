package com.communistutopia.spacetrader.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.communistutopia.spacetrader.view.ui.marketplace.MarketplaceBuyFragment
import com.communistutopia.spacetrader.view.ui.marketplace.MarketplaceSellFragment

class MarketplacePagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return if (position == 0) MarketplaceSellFragment() else MarketplaceBuyFragment()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0) "Sell" else "Buy"

    }
}