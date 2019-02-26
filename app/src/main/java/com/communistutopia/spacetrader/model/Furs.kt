package com.communistutopia.spacetrader.model

class Furs(val MTLP: Int, val MTLU: Int, val TTP: Int, val basePrice: Int, val IPL: Int, val variance: Int,
           val IE: Event, val CR: ResourceLevelType, val ER: ResourceLevelType, val MTL: Int, val MTH: Int)
    : TradeGood {
    override fun calculatePrice(good: TradeGood): Int {

    }
}