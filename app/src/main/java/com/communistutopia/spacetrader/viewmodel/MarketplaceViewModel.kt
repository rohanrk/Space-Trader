package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.Market
import com.communistutopia.spacetrader.model.Player
import com.communistutopia.spacetrader.model.*
import kotlin.properties.Delegates

class MarketplaceViewModel : ViewModel() {

    lateinit var player: Player
    lateinit var market: Market

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
    fun sellToPlayer(player: Player, tradeGood: TradeGood, numGoods: Int): Boolean {
        val total: Int = tradeGood.calculatePrice(market) * numGoods
        if (!canBeSold(tradeGood.MTLU) || player.credits < total || player.spaceship.hold.getValue(tradeGood) < numGoods) {
            return false
        } else {
            player.credits -= total
            market.inventory.removeSupplies(tradeGood, numGoods)
            player.spaceship.hold.addSupplies(tradeGood, numGoods)
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
    fun buyFromPlayer(player: Player, tradeGood: TradeGood, numGoods: Int): Boolean {
        val total: Int = tradeGood.calculatePrice(market) * numGoods
        if(player.availableCargo() < numGoods) {
            return false
        } else {
            player.credits += total
            market.inventory.addSupplies(tradeGood, numGoods)
            player.spaceship.hold.removeSupplies(tradeGood, numGoods)
            return true
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
            }
        }
    }

    /**
     * This method finds if an item can be sold on this planet based on this planet's tech level.
     * If the planet's tech level is below the MTLU, the item cannot be sold.
     */
    fun canBeSold(itemMTLU: Int): Boolean {
        if (market.techLevel.value() < itemMTLU) {
            return false
        }
        return true
    }

}
