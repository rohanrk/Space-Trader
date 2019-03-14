package com.communistutopia.spacetrader.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.adapter.MarketAction
import com.communistutopia.spacetrader.adapter.MarketItem
import com.communistutopia.spacetrader.adapter.MarketItemAdapter
import com.communistutopia.spacetrader.model.Market
import com.communistutopia.spacetrader.model.Player
import kotlinx.android.synthetic.main.marketplace_buy_fragment.*
import kotlinx.android.synthetic.main.marketplace_sell_fragment.*

/**
 * Fragment for buying items.
 */
class MarketplaceBuyFragment : Fragment() {

    companion object {
        fun newInstance() = MarketplaceBuyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.marketplace_buy_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var vm = activity as MarketplaceActivity

        val marketObserver = Observer<Market> { newMarket: Market? ->
            val marketItems: ArrayList<MarketItem> = ArrayList()
            // market.inventory
            val inventory = newMarket!!.inventory
            inventory.forEach { item ->
                if (item.value.amount > 0) {
                    marketItems.add(MarketItem(item.key, item.value.amount, item.value.calculatePrice(vm.viewModel.market), MarketAction.BUY))
                }
            }
            val adapter = MarketItemAdapter(context!!, marketItems, false)
            market_buy_list.adapter = adapter
        }

        vm.viewModel.marketObservable.observe(this, marketObserver)
    }
}