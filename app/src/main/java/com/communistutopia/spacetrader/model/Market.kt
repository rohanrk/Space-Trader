package com.communistutopia.spacetrader.model

/**
 * @param techLevel a TechLevel object
 * @param resourceLevel a ResourceLevel object
 * @param government a Government object
 *
 * All of these params will be used to calculate the relative cost of goods on a planet
 *
 * Initialization is done by the constructor, so all of these parameters are immutable properties
 *  of an instance of a Market once it is created
 */
class Market(val techLevel: TechLevel, val resourceLevel: ResourceLevel,
             val government: Government) {

    var event: Event
    var inventory = Inventory(0,0,0,0,0,0,0,0,0,
        0)

    init {
        event = Event.None
        initializeInventory()
    }
    constructor(techLevel: TechLevel, resourceLevel: ResourceLevel,
                government: Government, event: Event) : this(techLevel, resourceLevel, government) {
        this.event = event

    }

    override fun toString(): String {
        return "Market(" + "\n" +
                "techLevel=$techLevel, " + "\n" +
                "resourceLevel=$resourceLevel, " + "\n" +
                "government=$government" + "\n" +
                ")"
    }

    /**
     * Initializes the inventory. Goods that do not meet the minimum tech level are initialized to zero.
     * Goods that meet the tech level which produces the most are initialized to 5 * the base
     *
     */
    private fun initializeInventory() {
        val baseAmount = 20
        var water = baseAmount
        val initWater = Water(0,0,1,0,0,0, Event.None,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy))
        var furs = baseAmount
        val initFurs = Furs(0,0,4,0,0,0, Event.None,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy))
        var food = baseAmount
        val initFood = Food(0,0,1,0,0,0, Event.None,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy))
        var ore = baseAmount
        val initOre = Ore(4,0,6,0,0,0, Event.None,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy))
        var games = baseAmount
        val initGames = Games(6,0,7,0,0,0, Event.None,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy))
        var firearms = baseAmount
        val initFirearms = Firearms(3,0,7,0,0,0, Event.None,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy))
        var medicine = baseAmount
        val initMedicine = Medicine(4,0,7,0,0,0, Event.None,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy))
        var machines = baseAmount
        val initMachines = Machines(5,0,5,0,0,0, Event.None,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy))
        var narcotics = baseAmount
        val initNarcotics = Narcotics(1,0,1,0,0,0, Event.None,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy))
        var robots = baseAmount
        val initRobots = Robots(7,0,7,0,0,0, Event.None,
            ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
            0,0, Government(GovernmentType.Anarchy), Government(GovernmentType.Anarchy))

        if (!initWater.isMTLP(this)) {
            water = 0
        } else if (initWater.isTTP(this)) {
            water = baseAmount * 5
        }

        if (!initFurs.isMTLP(this)) {
            water = 0
        } else if (initFurs.isTTP(this)) {
            furs = baseAmount * 5
        }

        if (!initFood.isMTLP(this)) {
            food = 0
        } else if (initFood.isTTP(this)) {
            food = baseAmount * 5
        }

        if (!initOre.isMTLP(this)) {
            ore = 0
        } else if (initOre.isTTP(this)) {
            ore = baseAmount * 5
        }

        if (!initGames.isMTLP(this)) {
            games = 0
        } else if (initGames.isTTP(this)) {
            games = baseAmount * 5
        }

        if (!initFirearms.isMTLP(this)) {
            firearms = 0
        } else if (initFirearms.isTTP(this)) {
            firearms= baseAmount * 5
        }

        if (!initMedicine.isMTLP(this)) {
            medicine = 0
        } else if (initMedicine.isTTP(this)) {
            medicine = baseAmount * 5
        }

        if (!initMachines.isMTLP(this)) {
            machines = 0
        } else if (initMachines.isTTP(this)) {
            machines = baseAmount * 5
        }

        if (!initNarcotics.isMTLP(this)) {
            narcotics = 0
        } else if (initNarcotics.isTTP(this)) {
            narcotics = baseAmount * 5
        }

        if (!initRobots.isMTLP(this)) {
            robots = 0
        } else if (initRobots.isTTP(this)) {
            robots = baseAmount * 5
        }

        inventory = Inventory(water, furs, food, ore, games, firearms, medicine, machines, narcotics, robots)

    }

    /**
     * A function that sells goods to a Market
     * TODO add functionality to decrement quantity of good in player's cargo
     * TODO add functionality to increment quantity of good in this Market's inventory
     * @param player the player selling the good
     * @param tradeGood the good being sold
     * @param numGoods the number of things being sold
     */
    fun sell(player: Player, tradeGood: TradeGood, numGoods: Int) {
        player.credits += (tradeGood.calculatePrice(this) * numGoods)
    }

    /**
     * A function that sells goods to a Market
     * TODO add functionality to increment quantity of good in player's cargo
     * TODO add functionality to decrement quantity of good in this Market's inventory
     * @param player the player selling the good
     * @param tradeGood the good being sold
     * @param numGoods the number of things being sold
     */
    fun buy(player: Player, tradeGood: TradeGood, numGoods: Int) {
        player.credits -= (tradeGood.calculatePrice(this) * numGoods)
    }
}