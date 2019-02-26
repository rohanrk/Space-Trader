package com.communistutopia.spacetrader.model

/**
 * A superclass for all of the tradeable types of items. Contains methods for all types of
 *  tradeable bois to use when implemented
 *
 * @param MTLP = Minimum Tech Level to Produce this resource (You can't buy on planets below this level)
 * @param MTLU = Minimum Tech Level to Use this resource (You can't sell on planets below this level)
 * @param TTP = Tech Level which produces the most of this item
 * @param IPL = Price increase per tech level
 * @param variance = variance is the maximum percentage that the price can vary above or below the base
 * @param IE = Radical price increase event, when this even happens on a planet, the price may increase astronomically
 * @param CR = When this condition is present, the price of this resource is unusually low
 * @param ER = When this condition is present, the resource is expensive
 * @param MTL = Min price offered in space trade with random trader (not on a planet)
 * @param MTH = Max price offered in space trade with random trader (not on a planet)
 */

open class TradeGood(val MTLP: Int, val MTLU: Int, val TTP: Int, val basePrice: Int, val IPL: Int, val variance: Int,
                     val IE: Event, val CR: ResourceLevel, val ER: ResourceLevel, val MTL: Int, val MTH: Int) {

    /**
     * A method that calculates the price of the trade good and returns it
     *
     * @param market the Market from the planet being traded into
     */
    fun calculatePrice(
        market: Market
    ): Int {
        var price: Int = basePrice

        price += IPL * market.techLevel.value() //add price increase per techlevel

        if (market.event.equals(IE)) { //checks for radical price increase event
            price = basePrice + (basePrice * variance)
        }

        if (market.resourceLevel.equals(CR)) { //checks for abundance of resources
            price -= price * (variance / 2) //lower price accordingly
        } else if (market.resourceLevel.equals(ER)) { //checks for scarcity of resources
            price += price * (variance / 2) //increase price accordingly
        }

        //check if price is within acceptable variance range
        if (price > basePrice + (basePrice * variance)) {
            price = basePrice + (basePrice * variance)
        } else if (price < (basePrice - (basePrice * variance))) {
            price = basePrice - (basePrice * variance)
        }

        return price
    }
}