package com.communistutopia.spacetrader

/**
 * @author Nicholas Bayne Grubb
 * ngrubb3@gatech.edu
 */
import com.communistutopia.spacetrader.model.*
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

class PlanetAttributeTests {
    companion object {
        lateinit var government: Government
        lateinit var resourceLevel: ResourceLevel
        lateinit var techLevel: TechLevel

        @BeforeClass
        @JvmStatic
        fun setup() {
            government = Government(GovernmentType.Anarchy)
            resourceLevel = ResourceLevel(ResourceLevelType.DESERT)
            techLevel = TechLevel(TechLevelType.HiTech)
        }

        @AfterClass
        @JvmStatic
        fun teardown() {

        }
    }

    @Test
    fun testRandomTypeGenerators() {
        var governmentType = GovernmentType.randomGovernmentType()
        var resourceLevelType = ResourceLevelType.randomResourceLevelType()
        var techLevelType = TechLevelType.randomTechLevelType()
        assertTrue(governmentType is GovernmentType)
        assertTrue(resourceLevelType is ResourceLevelType)
        assertTrue(techLevelType is TechLevelType)
    }

    @Test
    fun testGovernmentHashCodeAndEquals() {
        var g1 = Government(GovernmentType.Anarchy)
        var g2 = Government(GovernmentType.Anarchy)
        var g1hash = g1.hashCode()
        var g2hash = g2.hashCode()
        assertEquals(g1hash, g2hash)
        assertTrue(g1.equals(g2))
    }

    @Test
    fun testResourceHashCodeAndEquals() {
        var r1 = ResourceLevel(ResourceLevelType.DESERT)
        var r2 = ResourceLevel(ResourceLevelType.DESERT)
        var r1hash = r1.hashCode()
        var r2hash = r2.hashCode()
        assertEquals(r1hash, r2hash)
        assertTrue(r1 == r2)
    }

    @Test
    fun testTechHashCodeAndEquals() {
        var a = TechLevel(TechLevelType.HiTech)
        var b = TechLevel(TechLevelType.HiTech)
        var ahash = a.hashCode()
        var bhash = b.hashCode()
        assertEquals(ahash, bhash)
        assertTrue(a == b)
    }

    @Test
    fun testGovernmentToString() {
        var str = government.toString()
        assertEquals(str, "Government(governmentType=Anarchy)")
    }

    @Test
    fun testResourceToString() {
        var str = resourceLevel.toString()
        assertEquals(str, "ResourceLevel(resourceLevel=DESERT)")
    }

    @Test
    fun testTechToString() {
        var str = techLevel.toString()
        assertEquals(str, "TechLevel(techLevel=HiTech)")
    }
}