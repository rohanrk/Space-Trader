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
    var inventory: Inventory

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
     * TODO implement this method to initialize the inventory
     */
    private fun initializeInventory() {

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