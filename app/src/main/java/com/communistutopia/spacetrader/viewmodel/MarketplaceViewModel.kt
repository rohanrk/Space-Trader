package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.Market
import com.communistutopia.spacetrader.model.Player

class MarketplaceViewModel : ViewModel() {

    lateinit var player: Player
    val playerObservable: MutableLiveData<Player> = MutableLiveData()
    lateinit var market: Market
    val marketObservable: MutableLiveData<Market> = MutableLiveData()
    private var prices: MutableMap<String, Int>

    init {
        prices = mutableMapOf()
    }

    val BASE_AMOUNT = 20

    /**
     * A function that buys goods from a Market
     * A player cannot buy items if they do not have enough cargo space.
     * TODO add error messages so the player knows why they cannot buy an item in view
     * @param player the player selling the good
     * @param tradeGood the good being sold
     * @param numGoods the number of things being sold
     * @return true if action was successful, false otherwise
     */
    fun sellToPlayer(tradeGood: String, numGoods: Int): Boolean {
        val total: Int = prices[tradeGood]!! * numGoods
        if (player.credits < total || player.availableCargo() < numGoods) {
            return false
        } else {
            player.credits -= total
            market.inventory.removeSupplies(tradeGood, numGoods)
            player.spaceship.hold.addSupplies(tradeGood, numGoods)
            playerObservable.value = player
            marketObservable.value = market
            return true
        }
    }

    /**
     * A function that sells goods to a Market
     * A player cannot sell a good if the planet's tech level is too low or if they do not have enough money
     * TODO add error messages so the player knows why they cannot sell an item in view
     * @param player the player selling the good
     * @param tradeGood the good being sold
     * @param numGoods the number of things being sold
     * @return true if action was successful, false otherwise
     */
    fun buyFromPlayer(tradeGood: String, minTechToBuy: Int, numGoods: Int): Boolean {
        val total: Int = prices[tradeGood]!! * numGoods
        return if(!canBeBought(minTechToBuy) || player.spaceship.hold.getValue(tradeGood) < numGoods) {
            false
        } else {
            player.credits += total
            market.inventory.addSupplies(tradeGood, numGoods)
            player.spaceship.hold.removeSupplies(tradeGood, numGoods)
            playerObservable.value = player
            marketObservable.value = market
            true
        }
    }

    /**
     * Goods that meet the tech level which produces the most are initialized to 5 * the base value.
     *
     */
    fun initializeInventory() {
        for (entry in market.inventory) {
            if (entry.value.isMTLP(market)) {
                entry.value.amount = BASE_AMOUNT * 5
                prices.put(entry.key, entry.value.calculatePrice(market))
            }
        }
    }

    /**
     * This method finds if the market can buy an item on this planet based on this planet's tech level.
     * If the planet's tech level is below the MTLU, the item cannot be sold.
     */
    fun canBeBought(itemMTLU: Int): Boolean {
        return market.techLevel.value() > itemMTLU
    }

    fun getPrice(item: String): Int {
        return prices[item]!!
    }
}
