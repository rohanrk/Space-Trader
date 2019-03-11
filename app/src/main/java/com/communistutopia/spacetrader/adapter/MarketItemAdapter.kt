package com.communistutopia.spacetrader.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.communistutopia.spacetrader.R

/**
 * Adapter for a list, used for the debug fragment
 * @author Drake Witt
 */
class MarketItemAdapter(private var context: Context,
                        private var dataSource: List<MarketItem>) : BaseAdapter() {

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
    val name: String, val quantity: Number, val price: Number, val action: MarketAction
)

