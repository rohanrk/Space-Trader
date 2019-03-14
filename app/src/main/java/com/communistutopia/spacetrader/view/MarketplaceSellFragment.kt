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
import kotlinx.android.synthetic.main.marketplace_sell_fragment.*

class MarketplaceSellFragment : Fragment() {

    companion object {
        fun newInstance() = MarketplaceSellFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.marketplace_sell_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var vm = activity as MarketplaceActivity

        val playerObserver = Observer<Player> { newPlayer: Player? ->
            val marketItems: ArrayList<MarketItem> = ArrayList()
            val inventory = newPlayer!!.spaceship.hold
            inventory.forEach { item ->
                if (item.value.amount > 0) {
                    marketItems.add(MarketItem(item.key, item.value.amount, item.value.calculatePrice(vm.viewModel.market), MarketAction.SELL))
                }
            }
            val adapter = MarketItemAdapter(context!!, marketItems, true)
            market_sell_list.adapter = adapter
        }

        vm.viewModel.playerObservable.observe(this, playerObserver)
    }
}
