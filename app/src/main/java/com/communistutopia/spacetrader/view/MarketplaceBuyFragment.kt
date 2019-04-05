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
import com.communistutopia.spacetrader.model.Player
import kotlinx.android.synthetic.main.marketplace_buy_fragment.*

/**
 * Fragment for buying items.
 *
 * @author Drake Witt
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
        val vm = activity as MarketplaceActivity

        val marketObserver = Observer<Player> { newPlayer: Player? ->
            val newMarket = newPlayer!!.location.market
            val marketItems: ArrayList<MarketItem> = ArrayList()
            // market.inventory
            val inventory = newMarket.inventory
            inventory.forEach { item ->
                if (item.value.amount > 0) {
                    marketItems.add(MarketItem(item.key, item.value.amount, item.value.calculatePrice(vm.viewModel.player.value!!.location.market), MarketAction.BUY))
                }
            }
            val adapter = MarketItemAdapter(context!!, marketItems, false)
            market_buy_list.adapter = adapter
        }

        vm.viewModel.player.observe(this, marketObserver)
    }
}