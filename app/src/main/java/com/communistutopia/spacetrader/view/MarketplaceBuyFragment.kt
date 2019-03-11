package com.communistutopia.spacetrader.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.adapter.MarketAction
import com.communistutopia.spacetrader.adapter.MarketItem
import com.communistutopia.spacetrader.adapter.MarketItemAdapter
import com.communistutopia.spacetrader.viewmodel.MarketplaceViewModel
import kotlinx.android.synthetic.main.marketplace_buy_fragment.*

/**
 * Fragment for buying items.
 */
class MarketplaceBuyFragment : Fragment() {

    companion object {
        fun newInstance() = MarketplaceBuyFragment()
    }

    private lateinit var viewModel: MarketplaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.marketplace_buy_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MarketplaceViewModel::class.java)
        // TODO: Use the ViewModel

        // Below is just dummy data for the purpose of showing that the adapter works.
        // TODO: Replace this with functioning information (in the ViewModel)
        val marketItems: ArrayList<MarketItem> = ArrayList<MarketItem>()
        marketItems.add(MarketItem("Water", 10, 2, MarketAction.BUY))
        marketItems.add(MarketItem("Robots", 4, 68, MarketAction.BUY))
        marketItems.add(MarketItem("Medicine", 3, 12, MarketAction.BUY))
        marketItems.add(MarketItem("Narcotics", 40, 32, MarketAction.BUY))

        // Create the adapter with our dummy data and bind it to the view
        val adapter = MarketItemAdapter(context!!, marketItems)
        market_buy_list.adapter = adapter
    }

}
