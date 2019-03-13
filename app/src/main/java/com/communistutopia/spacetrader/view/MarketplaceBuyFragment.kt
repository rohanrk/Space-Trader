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
import com.communistutopia.spacetrader.model.Market
import com.communistutopia.spacetrader.viewmodel.MarketplaceViewModel
import kotlinx.android.synthetic.main.marketplace_buy_fragment.*

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

        val marketItems: ArrayList<MarketItem> = ArrayList<MarketItem>()
        var vm = activity as MarketplaceActivity
        for (item in vm.viewModel.market.inventory) {
            if (item.value.amount > 0) {
                marketItems.add(MarketItem(item.key, item.value.amount, item.value.calculatePrice(vm.viewModel.market), MarketAction.BUY))
            }
        }

        // Create the adapter with our dummy data and bind it to the view
        val adapter = MarketItemAdapter(context!!, marketItems)
        market_buy_list.adapter = adapter
    }

}
