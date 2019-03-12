package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.Market
import com.communistutopia.spacetrader.model.Player
import com.communistutopia.spacetrader.model.*

class MarketplaceViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var market: Market
    lateinit var player: Player

    init {
        market = Market(TechLevel(TechLevelType.randomTechLevelType()), ResourceLevel(ResourceLevelType.randomResourceLevelType()), Government(GovernmentType.randomGovernmentType()))

    }

    fun triggerBuy() {

    }

    fun triggerSell() {

    }


}
