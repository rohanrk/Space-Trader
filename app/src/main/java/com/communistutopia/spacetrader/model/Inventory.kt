package com.communistutopia.spacetrader.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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
 * @constructor Creates inventory with 0 goods
 * @author Rohan Rk <rohanrk@gatech.edu>
 */
@Parcelize
class Inventory: Parcelable, Iterable<MutableMap.MutableEntry<String, TradeGood>> {


    private var supplies: MutableMap<String, TradeGood>

    init {
        supplies = mutableMapOf()
        initialize()
    }

    /**
     * Initializes the inventory. All goods are initialized to 0.
     * The market or cargo hold will modify this logic as needed
     */
    private fun initialize() {
        val initWater = Water(0,0,2,30,3,4, Event.Drought,
            ResourceLevel(ResourceLevelType.LOTSOFWATER), ResourceLevel(ResourceLevelType.DESERT),
            30,50, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), 0)
        supplies[initWater.name] = initWater

        val initFurs = Furs(0,0,0,250,10,10, Event.Cold,
            ResourceLevel(ResourceLevelType.RICHFAUNA), ResourceLevel(ResourceLevelType.LIFELESS),
            230,280, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), 0)
        supplies[initFurs.name] = initFurs

        val initFood = Food(1,0,1,100,5,5, Event.CropFailure,
            ResourceLevel(ResourceLevelType.RICHSOIL), ResourceLevel(ResourceLevelType.POORSOIL),
            90,160, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), 0)
        supplies[initFood.name] = initFood

        val initOre = Ore(2,2,3,350,20,10, Event.War,
            ResourceLevel(ResourceLevelType.MINERALRICH), ResourceLevel(ResourceLevelType.MINERALPOOR),
            350,420, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), 0)
        supplies[initOre.name] = initOre

        val initGames = Games(3, 1, 6 ,350,-10, 5, Event.Boredom,
            ResourceLevel(ResourceLevelType.ARTISTIC), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            160,270, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), 0)
        supplies[initGames.name] = initGames

        val initFirearms = Firearms(3, 1,5, 1250,-75,100, Event.War,
            ResourceLevel(ResourceLevelType.WARLIKE), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            600,1100, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), 0)
        supplies[initFirearms.name] = initFirearms

        val initMedicine = Medicine(4, 1,6,650,-20,10, Event.None,
            ResourceLevel(ResourceLevelType.LOTSOFHERBS), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            400,700, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), 0)
        supplies[initMedicine.name] = initMedicine

        val initMachines = Machines(4,3,5,900,-30,5, Event.LackOfWorkers,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            600,800, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), 0)
        supplies[initMachines.name] = initMachines

        val initNarcotics = Narcotics(5,0,5,3500,-125,150, Event.Boredom,
            ResourceLevel(ResourceLevelType.WEIRDMUSHROOMS), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            2000,3000, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), 0)
        supplies[initNarcotics.name] = initNarcotics

        val initRobots = Robots(6,4,7,5000,-150,100, Event.LackOfWorkers,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), 0)
        supplies[initRobots.name] = initRobots
    }

    /**
     * Iterator method allows Inventory to iterate through supplies without accessing private variable
     */
    override fun iterator(): Iterator<MutableMap.MutableEntry<String, TradeGood>> {
        return supplies.iterator()
    }

    override fun toString(): String {
        return supplies.toString()
    }

    fun getAmount(good: String): Int {
        return supplies[good]!!.amount
    }

    fun getValue(good: String): Int {
        return supplies[good]!!.basePrice
    }

    fun getMTLP(good: String): Int {
        return supplies[good]!!.MTLP
    }

    fun getMTLU(good: String): Int {
        return supplies[good]!!.MTLU
    }

    fun addSupplies(good: String, amount: Int) {
        val numGoods = supplies[good]!!.amount
        supplies[good]!!.amount = numGoods + amount
    }

    fun removeSupplies(good: String, amount: Int) {
        val numGoods = supplies[good]!!.amount
        supplies[good]!!.amount = numGoods - amount
    }

    fun getTotalSupplies(): Int {
        return supplies.map { it.value.amount }.sum()
    }
}

