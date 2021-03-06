package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.repository.PlayerRepository

/**
 * Viewmodel that handles all marketplace functions
 *
 * @author Rohan Rk <rohanrk@gatech.edu>
 */
class MarketplaceViewModel : ViewModel() {
    val player = PlayerRepository.player

    private var prices: MutableMap<String, Int> = mutableMapOf()

    private val baseAmount = 20

    /**
     * A function that buys goods from a Market
     * A player cannot buy items if they do not have enough cargo space.
     * @param tradeGood the good being sold
     * @param numGoods the number of things being sold
     * @return true if action was successful, false otherwise
     */
    fun sellToPlayer(tradeGood: String, numGoods: Int): Boolean {
        val total: Int = prices[tradeGood]!! * numGoods
        return if (player.value!!.credits < total || player.value!!.spaceship.cargoCapacity < numGoods) {
            false
        } else {
            player.value!!.credits -= total
            player.value!!.location.market.inventory.removeSupplies(tradeGood, numGoods)
            player.value!!.spaceship.hold.addSupplies(tradeGood, numGoods)
            player.value!!.spaceship.cargoCapacity--
            player.value = player.value
            true
        }
    }

    /**
     * A function that sells goods to a Market
     * A player cannot sell a good if the planet's tech level is too low or if they do not have enough money
     * TODO add error messages so the player knows why they cannot sell an item in view
     * @param tradeGood the good being sold
     * @param numGoods the number of things being sold
     * @return true if action was successful, false otherwise
     */
    fun buyFromPlayer(tradeGood: String, minTechToBuy: Int, numGoods: Int): Boolean {
        val total: Int = prices[tradeGood]!! * numGoods
        return if(!canBeBought(minTechToBuy) || player.value!!.spaceship.hold.getValue(tradeGood) < numGoods) {
            false
        } else {
            player.value!!.credits += total
            player.value!!.location.market.inventory.addSupplies(tradeGood, numGoods)
            player.value!!.spaceship.hold.removeSupplies(tradeGood, numGoods)
            player.value!!.spaceship.cargoCapacity++
            player.value = player.value
            true
        }
    }

    /**
     * Goods that meet the tech level which produces the most are initialized to 5 * the base value.
     * Goods that do not meet the MTLP are kept at 0
     * The rest are set to the base amount
     */
    fun initializeInventory() {
        for (entry in player.value!!.location.market.inventory) {
            when {
                entry.value.isTTP(player.value!!.location.market) -> entry.value.amount = baseAmount * 5
                entry.value.isMTLP(player.value!!.location.market) -> entry.value.amount = baseAmount
                else -> entry.value.amount = 0
            }
            prices[entry.key] = entry.value.calculatePrice(player.value!!.location.market)
        }
    }

    /**
     * This method finds if the market can buy an item on this planet based on this planet's tech level.
     * If the planet's tech level is below the MTLU, the item cannot be sold.
     */
    @Suppress("KotlinDeprecation")
    private fun canBeBought(itemMTLU: Int): Boolean {
        return player.value!!.location.market.techLevel!!.value() > itemMTLU
    }

    fun getPrice(item: String): Int {
        return prices[item]!!
    }
}
