package com.communistutopia.spacetrader.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.model.SolarSystem

/**
 * Adapter for a list, used for the debug fragment
 */
class SolarSystemAdapter(private var context: Context,
                         private var dataSource: List<SolarSystem>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var objString: TextView? = null

        init {
            this.objString = row?.findViewById<TextView>(R.id.obj_string)
        }
    }

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item_solarsystem, parent, false)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var solarSystem = dataSource[position]
        viewHolder.objString?.text = solarSystem.toString()

        return view as View
    }

    override fun getItem(position: Int): SolarSystem {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}
