@file:Suppress("KotlinDeprecation")

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
 * @param GTD = Government Type which heavily demands this resource, when the same as the market, increase price
 * @param GTS = Government Type which heavily supplies this resource, when the same as the market, decrease price
 */

@Suppress("KotlinDeprecation", "KotlinDeprecation")
class TradeGood(
    var MTLP: Int,
    var MTLU: Int,
    private var TTP: Int,
    var basePrice: Int,
    private var IPL: Int,
    var variance: Int,
    var IE: Event,
    var CR: ResourceLevel,
    var ER: ResourceLevel,
    private var MTL: Int,
    private var MTH: Int,
    var GTD: Government,
    var GTS: Government,
    var name: String,
    var amount: Int
) {

    // this is to make Firebase happy
    constructor(): this(0, 0, 0, 10, 0, 0,
        Event.None, ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
        ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), 0, 0, Government(GovernmentType.Anarchy),
        Government(GovernmentType.Anarchy), "", 0)

    private var minPrice = 20
    /**
     * A method that calculates the price of the trade good and returns it
     * Prices are calculated by the following model
     * price =
     *
     * @param market the Market from the planet being traded into
     */
    @Suppress(
        "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
        "KotlinDeprecation"
    )
    fun calculatePrice(market: Market): Int {
        var price: Int = basePrice

        @Suppress("KotlinDeprecation")
        price += IPL * market.techLevel.value() //add price increase per techlevel

        if (market.event == (IE)) { //checks for radical price increase event
            price += (price * variance)
        }

        if (market.resourceLevel == CR) { //checks for abundance of resources
            price -= ((price * variance) / 20) //lower price accordingly
        } else if (market.resourceLevel == ER) { //checks for scarcity of resources
            price += ((price * variance) / 20) //increase price accordingly
        }

        if (market.government == GTD) { //checks for government type demanding
            price += ((price * variance) / 20) //increase price accordingly
        } else if (market.government == GTS) { //checks for government type supplying
            price -= ((price * variance) / 20) // decrease price accordingly
        }

        //check if price is within acceptable variance range
        if (price > (basePrice * variance)) {
            price = (basePrice * variance)
        } else if (price < ((basePrice / variance))) {
            price = (basePrice / variance)
        }

        return if (price > 0) price else minPrice // Closest thing to a ternary operator in Kotlin
    }

    /**
     * A method that returns whether the MTLP equals that of a market
     *
     * @param market the Market from the planet being traded into
     *
     *
     */
    fun isMTLP(market: Market): Boolean {
        return MTLP <= market.techLevel!!.value()
    }

    /**
     * A method that returns whether the TTP equals that of a market
     *
     * @param market the Market from the planet being traded into
     *
     *
     */
    fun isTTP(market: Market): Boolean {
        return TTP == market.techLevel!!.value()
    }

}