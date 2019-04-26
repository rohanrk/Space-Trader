package com.communistutopia.spacetrader

import com.communistutopia.spacetrader.model.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Tests the TradeGood class (currently only for calculatePRice)
 */
class TradeGoodTest {

    lateinit var market: Market
    lateinit var tradeGood: TradeGood

    /**
     * Calls before each test to reset the objects
     */
    @Before
    fun initObjects() {
        market = Market(
            techLevel = TechLevel(TechLevelType.Agricultural), // modifier of * 1
            resourceLevel = ResourceLevel(ResourceLevelType.ARTISTIC),
            government = Government(GovernmentType.CapitalistState)
        )
        tradeGood = TradeGood(
            MTLP = 0,
            MTLU = 0,
            TTP = 0,
            basePrice = 50,
            IPL = 0, // price increase per tech level
            variance = 2, // maximum multiplier cna vary above the base
            IE = Event.War, // Event
            CR = ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), // when present, price is low
            ER = ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES), // when present, price is expensive
            MTL = 0,
            MTH = 0,
            GTD = Government(GovernmentType.Anarchy), // government type that demands
            GTS = Government(GovernmentType.Anarchy), // gov type that supplies
            name = "BONELESS PIZZA",
            amount = 10
        )
    }

    /**
     * Sets a market event so the price increases
     */
    @Test
    fun calcPricePriceIncrease() {
        // Make sure every event except none increases the price if matched with market event
        Event.values().filter { (it != Event.None) }.forEach { event ->
            market.event = event
            tradeGood.IE = event
            val price = tradeGood.calculatePrice(market)
            assertEquals(150, price)
        }
    }

    /**
     * No market event present to increase the price
     */
    @Test
    fun calcPriceNoPriceIncrease() {
        var price = 0

        market.event = Event.None
        tradeGood.IE = Event.Boredom
        price = tradeGood.calculatePrice(market)
        assertEquals(50, price)

        market.event = Event.None
        tradeGood.IE = Event.None
        price = tradeGood.calculatePrice(market)
        assertEquals(50, price)
    }

    /**
     * Resource abundance variable should lower the price
     */
    @Test
    fun calcPriceResourceAbundance() {
        ResourceLevelType.values().filter { (it != ResourceLevelType.NOSPECIALRESOURCES) }.forEach { resource ->
            market.resourceLevel = ResourceLevel(resource)
            tradeGood.CR = ResourceLevel(resource)
            val price = tradeGood.calculatePrice(market)
            assertEquals(45, price)
        }

        // If resource and CR are different, no price increase
        initObjects()
        var price = tradeGood.calculatePrice(market)
        assertEquals(50, price)

        // If both are none, price shouldn't decrease
        market.resourceLevel = ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES)
        tradeGood.CR = ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES)
        price = tradeGood.calculatePrice(market)
        assertEquals(50, price)
    }

    /**
     * Scarcity variable should increase the price
     */
    @Test
    fun calcPriceResourceScarcity() {
        ResourceLevelType.values().filter { (it != ResourceLevelType.NOSPECIALRESOURCES) }.forEach { resource ->
            market.resourceLevel = ResourceLevel(resource)
            tradeGood.ER = ResourceLevel(resource)
            val price = tradeGood.calculatePrice(market)
            assertEquals(55, price)
        }

        // different resourceLevel and ER shouldn't increase
        var price = tradeGood.calculatePrice(market)
        assertEquals(50, price)

        // If both are none, price shouldn't increase
        market.resourceLevel = ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES)
        tradeGood.ER = ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES)
        price = tradeGood.calculatePrice(market)
        assertEquals(50, price)
    }

    /**
     * Government demanding variable should increase the price
     */
    @Test
    fun calcPriceGovDemanding() {
        GovernmentType.values().forEach { gov ->
            market.government = Government(gov)
            tradeGood.GTD = Government(gov)
            val price = tradeGood.calculatePrice(market)
            assertEquals(55, price)
        }

        // different govs shouldn't increase price
        market.government = Government(GovernmentType.Anarchy)
        tradeGood.GTD = Government(GovernmentType.CommunistState)
        val price = tradeGood.calculatePrice(market)
        assertEquals(50, price)
    }

    /**
     * Government supplying variable should lower the price
     */
    @Test
    fun calcPriceGovSupplying() {
        GovernmentType.values().forEach { gov ->
            market.government = Government(gov)
            tradeGood.GTS = Government(gov)
            val price = tradeGood.calculatePrice(market)
            assertEquals(45, price)
        }

        // Different gov types shouldn't increase price
        market.government = Government(GovernmentType.Dictatorship)
        tradeGood.GTS = Government(GovernmentType.CommunistState)
        val price = tradeGood.calculatePrice(market)
        assertEquals(50, price)
    }

    /**
     * According to method, price shouldn't exceed the basePrice * variance multiplier
     */
    @Test
    fun checkVarianceRange() {
        market.techLevel = TechLevel(TechLevelType.HiTech)
        market.resourceLevel = ResourceLevel(ResourceLevelType.MINERALRICH)
        tradeGood.ER = ResourceLevel(ResourceLevelType.MINERALRICH)
        market.government = Government(GovernmentType.Anarchy)
        tradeGood.GTD = Government(GovernmentType.Anarchy)
        market.resourceLevel = ResourceLevel(ResourceLevelType.MINERALRICH)
        tradeGood.ER = ResourceLevel(ResourceLevelType.MINERALRICH)
        var price = tradeGood.calculatePrice(market)
        assertEquals(100, price)

        market.techLevel = TechLevel(TechLevelType.PreAgricultural)
        market.resourceLevel = ResourceLevel(ResourceLevelType.MINERALRICH)
        tradeGood.CR = ResourceLevel(ResourceLevelType.MINERALRICH)
        market.government = Government(GovernmentType.Anarchy)
        tradeGood.GTS = Government(GovernmentType.Anarchy)
        price = tradeGood.calculatePrice(market)
        assertEquals(25, price)
    }
}