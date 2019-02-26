package com.communistutopia.spacetrader.model

open class TradeGood(val MTLP: Int, val MTLU: Int, val TTP: Int, val basePrice: Int, val IPL: Int, val variance: Int,
                     val IE: Event, val CR: ResourceLevel, val ER: ResourceLevel, val MTL: Int, val MTH: Int) {
    fun calculatePrice(
        market: Market
    ): Int {
        var price: Int = basePrice
        price += IPL * market.techLevel.value() //add price increase per techlevel
        if (market.event.equals(IE)) {
            price = basePrice + (basePrice * variance)
        }

        if (market.resourceLevel.equals(CR)) {
            price -= price * (variance / 2)
        } else if (market.resourceLevel.equals(ER)) {
            price += price * (variance / 2)
        }

        if (price > basePrice + (basePrice * variance)) {
            price = basePrice + (basePrice * variance)
        } else if (price < (basePrice - (basePrice * variance))) {
            price = basePrice - (basePrice * variance)
        }

        return price
    }
}