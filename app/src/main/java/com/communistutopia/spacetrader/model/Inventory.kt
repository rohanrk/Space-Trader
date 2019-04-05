package com.communistutopia.spacetrader.model

/**
 * A class to hold the number of each trade good a player, planet, or trader has
 * @constructor Creates inventory with 0 goods
 * @author Rohan Rk <rohanrk@gatech.edu>
 */
class Inventory: Iterable<MutableMap.MutableEntry<String, TradeGood>> {
    private var supplies: MutableMap<String, TradeGood> = mutableMapOf()

    init {
        initialize()
    }

    /**
     * Initializes the inventory. All goods are initialized to 0.
     * The market or cargo hold will modify this logic as needed
     */
    private fun initialize() {
        val initWater = TradeGood(0,0,2,30,3,4, Event.Drought,
            ResourceLevel(ResourceLevelType.LOTSOFWATER), ResourceLevel(ResourceLevelType.DESERT),
            30,50, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), "Water", 0)
        supplies[initWater.name] = initWater

        val initFurs = TradeGood(0,0,0,250,10,10, Event.Cold,
            ResourceLevel(ResourceLevelType.RICHFAUNA), ResourceLevel(ResourceLevelType.LIFELESS),
            230,280, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), "Furs", 0)
        supplies[initFurs.name] = initFurs

        val initFood = TradeGood(1,0,1,100,5,5, Event.CropFailure,
            ResourceLevel(ResourceLevelType.RICHSOIL), ResourceLevel(ResourceLevelType.POORSOIL),
            90,160, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), "Food", 0)
        supplies[initFood.name] = initFood

        val initOre = TradeGood(2,2,3,350,20,10, Event.War,
            ResourceLevel(ResourceLevelType.MINERALRICH), ResourceLevel(ResourceLevelType.MINERALPOOR),
            350,420, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), "Ore", 0)
        supplies[initOre.name] = initOre

        val initGames = TradeGood(3, 1, 6 ,350,-10, 5, Event.Boredom,
            ResourceLevel(ResourceLevelType.ARTISTIC), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            160,270, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), "Games", 0)
        supplies[initGames.name] = initGames

        val initFirearms = TradeGood(3, 1,5, 1250,-75,100, Event.War,
            ResourceLevel(ResourceLevelType.WARLIKE), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            600,1100, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), "Firearms", 0)
        supplies[initFirearms.name] = initFirearms

        val initMedicine = TradeGood(4, 1,6,650,-20,10, Event.None,
            ResourceLevel(ResourceLevelType.LOTSOFHERBS), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            400,700, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), "Medicine", 0)
        supplies[initMedicine.name] = initMedicine

        val initMachines = TradeGood(4,3,5,900,-30,5, Event.LackOfWorkers,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            600,800, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), "Machines", 0)
        supplies[initMachines.name] = initMachines

        val initNarcotics = TradeGood(5,0,5,3500,-125,150, Event.Boredom,
            ResourceLevel(ResourceLevelType.WEIRDMUSHROOMS), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            2000,3000, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), "Narcotics", 0)
        supplies[initNarcotics.name] = initNarcotics

        val initRobots = TradeGood(6,4,7,5000,-150,100, Event.LackOfWorkers,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy), "Robots", 0)
        supplies[initRobots.name] = initRobots
    }

    fun removeHalfOfCargo() {
        for ((_, cargoItem) in supplies.toList()) {
            cargoItem.amount /= 2
        }
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

