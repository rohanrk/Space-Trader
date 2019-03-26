package com.communistutopia.spacetrader.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.communistutopia.spacetrader.view.MarketplaceBuyFragment
import com.communistutopia.spacetrader.view.MarketplaceSellFragment

/**
 * This serves as an adapter for both the Buy and Sell tabs within the marketplace.
 * This lets the tabs and scrolling between the two fragments work.
 * @author Drake Witt
 */
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