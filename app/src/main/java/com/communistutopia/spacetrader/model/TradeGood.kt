package com.communistutopia.spacetrader.model

data class TradeGood(val MTLP: Int, val MTLU: Int, val TTP: Int, val basePrice: Int, val IPL: Int, val variance: Int,
                     val IE: Event, val CR: ResourceLevel, val ER: ResourceLevel, val MTL: Int, val MTH: Int) {
    fun calculatePrice(
        good: TradeGood,
        market: Market
    ): Int {
        var price: Int = basePrice
        price += IPL * market.techLevel.value() //add price increase per techlevel
        if (market.event.equals(IE)) {
            price = basePrice + (price * variance)
        }

        if (market.resourceLevel.equals(CR))


        return price
    }
}