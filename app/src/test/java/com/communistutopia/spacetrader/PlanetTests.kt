package com.communistutopia.spacetrader

import com.communistutopia.spacetrader.model.*
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlanetTests {
    companion object {
        lateinit var planet: Planet

        @BeforeClass
        @JvmStatic
        fun setup() {
            planet = Planet(
                "Bayne",
                TechLevel(TechLevelType.HiTech),
                ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
                Government(GovernmentType.Anarchy)
            )
        }

        @AfterClass
        @JvmStatic
        fun teardown() {

        }
    }

    @Test
    fun testRandomEvent() {
        var event = planet.rollForRandomEvent()
        assertTrue(event is Event)
    }

    @Test
    fun testRandomPirate() {
        var pirateEncounter = planet.rollForPirates()
        assertTrue(pirateEncounter is Boolean)
    }

    @Test
    fun testRandomPolice() {
        var policeEncounter = planet.rollForPolice()
        assertTrue(policeEncounter is Boolean)
    }

    @Test
    fun testToString() {
        var toString = planet.toString()
        var toStringResult = "Planet(" + "\n" +
                "name='Bayne', " + "\n" +
                "techLevel=TechLevel(techLevel=HiTech), " + "\n" +
                "resourceLevel=ResourceLevel(resourceLevel=NOSPECIALRESOURCES), " + "\n" +
                "government=Government(governmentType=Anarchy), " + "\n" +
                "market=Market(\ntechLevel=TechLevel(techLevel=HiTech), \n" +
                "resourceLevel=ResourceLevel(resourceLevel=NOSPECIALRESOURCES), \n" +
                "government=Government(governmentType=Anarchy)\n)" + "\n" +
                ")"
        assertEquals(toString, toStringResult)
    }
}
