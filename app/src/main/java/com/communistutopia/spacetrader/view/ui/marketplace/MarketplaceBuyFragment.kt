package com.communistutopia.spacetrader.view.ui.marketplace

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.communistutopia.spacetrader.R

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
    }

}
