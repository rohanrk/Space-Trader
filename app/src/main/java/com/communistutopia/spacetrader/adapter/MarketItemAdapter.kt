package com.communistutopia.spacetrader.adapter

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.viewmodel.MarketplaceViewModel

/**
 * Adapter for a list, used for the debug fragment
 * @param context The current view containing the adapter
 * @param dataSource The structure containing the data for the adaper to display
 * @param sell boolean representing if market is selling or buying
 * @author Drake Witt
 */
class MarketItemAdapter(private var context: Context,
                        private var dataSource: List<MarketItem>, private var sell: Boolean) : BaseAdapter() {

    private var marketplaceViewModel: MarketplaceViewModel

    private class ViewHolder(row: View?) {
        var name: TextView? = null
        var quantity: TextView? = null
        var price: TextView? = null
        var actionButton: Button? = null


        /**
         * Get the components of the row
         */
        init {
            this.name = row?.findViewById<TextView>(R.id.item_name)
            this.quantity = row?.findViewById<TextView>(R.id.item_quantity)
            this.price = row?.findViewById<TextView>(R.id.item_price)
            this.actionButton = row?.findViewById<Button>(R.id.item_action_button)

        }
    }


    init {
        marketplaceViewModel = ViewModelProviders.of(context as FragmentActivity).get(MarketplaceViewModel::class.java)
    }

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item_market, parent, false)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = dataSource[position]
        viewHolder.name?.text = item.name
        viewHolder.quantity?.text = item.quantity.toString()
        viewHolder.price?.text = item.price.toString() + " credits"
        viewHolder.actionButton?.text = item.action.toString()

        if (sell) {
            viewHolder.actionButton?.setOnClickListener {
                if (marketplaceViewModel.buyFromPlayer(item.name,
                        marketplaceViewModel.player.value!!.location.market.inventory.getMTLU(item.name), 1)) {
                    item.quantity = marketplaceViewModel.player.value!!.location.market.inventory.getAmount(item.name)
                    viewHolder.quantity?.text = item.quantity.toString()
                    val out: String = "You now have %d credits and %d %s resource"
                        .format(marketplaceViewModel.player.value!!.credits,
                        marketplaceViewModel.player.value!!.spaceship.hold.getAmount((item.name)), item.name)
                    Toast.makeText(context, out, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Cannot sell item", Toast.LENGTH_LONG).show()
                }
            }

        } else {
            viewHolder.actionButton?.setOnClickListener {
                if (marketplaceViewModel.sellToPlayer(item.name, 1)) {
                    item.quantity = marketplaceViewModel.player.value!!.location.market.inventory.getAmount(item.name)
                    viewHolder.quantity?.text = item.quantity.toString()
                    val out: String = "You now have %d credits and %d %s resource"
                        .format(marketplaceViewModel.player.value!!.credits,
                        marketplaceViewModel.player.value!!.spaceship.hold.getAmount((item.name)), item.name)
                    Toast.makeText(context, out, Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(context, "Cannot buy item", Toast.LENGTH_LONG).show()
                }
            }
        }
        return view as View
    }

    override fun getItem(position: Int): MarketItem {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}

/**
 * THESE ARE TEMPORARY! They should be refactored out into the model.
 * There probably is a better design way of doing this too using existing data things.
 * This is merely a way to test the proof of concept for the page.
 */
enum class MarketAction {
    BUY, SELL
}

data class MarketItem (
    val name: String, var quantity: Number, val price: Number, val action: MarketAction
)

