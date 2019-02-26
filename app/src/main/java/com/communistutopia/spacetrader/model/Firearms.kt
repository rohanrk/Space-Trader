package com.communistutopia.spacetrader.model

/**
 * subclass of TradeGood, used to represent a good of type Firearms
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

class Firearms(MTLP: Int, MTLU: Int, TTP: Int, basePrice: Int, IPL: Int, variance: Int, IE: Event, CR: ResourceLevel,
               ER: ResourceLevel, MTL: Int, MTH: Int
) : TradeGood(MTLP, MTLU, TTP, basePrice, IPL, variance, IE, CR, ER, MTL, MTH)