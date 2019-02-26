package com.communistutopia.spacetrader.model

interface TradeGood {
    fun calculatePrice(good: TradeGood): Int
}