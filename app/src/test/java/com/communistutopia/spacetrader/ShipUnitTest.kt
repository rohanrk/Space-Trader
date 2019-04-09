package com.communistutopia.spacetrader

import com.communistutopia.spacetrader.model.Ship
import com.communistutopia.spacetrader.model.SolarSystem
import org.junit.Test

import org.junit.Assert.*

class ShipUnitTest {
    @Test
    fun canTravelTo_isCorrect() {
        val testShip = Ship()
        val system1 = SolarSystem(ArrayList(), "", 0, 0)
        val system2 = SolarSystem(ArrayList(), "", 1, 0)
        val system3 = SolarSystem(ArrayList(), "", 100, 0)
        assertEquals(testShip.canTravelTo(system1, system2), true)
        assertEquals(testShip.canTravelTo(system1, system3), false)
    }
}