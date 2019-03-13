package com.communistutopia.spacetrader.model

/**
 * A class to hold the number of each trade good a player, planet, or trader has
 * @param water int representing the number of goods in this inventory
 * @param furs int representing the number of goods in this inventory
 * @param food int representing the number of goods in this inventory
 * @param ore int representing the number of goods in this inventory
 * @param games int representing the number of goods in this inventory
 * @param firearms int representing the number of goods in this inventory
 * @param medicine int representing the number of goods in this inventory
 * @param machines int representing the number of goods in this inventory
 * @param narcotics int representing the number of goods in this inventory
 * @param robots int representing the number of goods in this inventory
 *
 */
class Inventory(
    var water: Int, var furs: Int, var food: Int, var ore: Int, var games: Int, var firearms: Int,
                var medicine: Int, var machines: Int, var narcotics: Int, var robots: Int) {


    private var supplies: MutableMap<String, Int>

    init {
        //TODO: Refactor this. It's real bad hardcoding but should be acceptable for the purposes of M7 demo
        supplies = mutableMapOf("water" to water, "furs" to furs, "food" to food, "ore" to ore, "games" to games, "firearms" to firearms,
                        "medicine" to medicine, "machines" to machines, "narcotics" to narcotics, "robots" to robots)
    }

    fun getValue(good: TradeGood): Int {
        return supplies[good.name]!!
    }

    fun addSupplies(good: TradeGood, amount: Int) {
        val numGoods = supplies[good.name]!!
        supplies[good.name] = numGoods + amount
    }

    fun removeSupplies(good: TradeGood, amount: Int) {
        val numGoods = supplies[good.name]!!
        supplies[good.name] = numGoods - amount
    }

    fun getTotalSupplies(): Int {
        return supplies.map { it.value }.sum()
    }
}

