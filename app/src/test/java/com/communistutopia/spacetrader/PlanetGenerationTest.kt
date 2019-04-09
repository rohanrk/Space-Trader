package com.communistutopia.spacetrader

import com.communistutopia.spacetrader.model.*
import org.junit.Assert
import org.junit.Test
import com.communistutopia.spacetrader.model.Universe.generatePlanets


/**
 * Tests number of planets generated and all attributes of planet class
 *
 */
class PlanetGenerationTest {

    private var planets = generatePlanets(1)
    var planet = planets[0]
    private var noPlanets = generatePlanets(0)

    @Test
    fun onePlanetGeneration() {
        Assert.assertEquals(1, planets.size)
    }

    @Test
    fun zeroPlanetGeneration(){
        Assert.assertEquals(0, noPlanets.size)
    }

    @Test
    fun planetNameCorrect(){
        Assert.assertEquals("Carzon", planet.getPName())
    }

    @Test
    fun govtTypeCorrect(){
        Assert.assertEquals(GovernmentType.CorporateState, planet.getGType())
    }

    @Test
    fun techLevelCorrect(){
        Assert.assertEquals(TechLevelType.EarlyIndustrial, planet.getTech())
    }

    @Test
    fun resourceLevelCorrect(){
        Assert.assertEquals(ResourceLevelType.POORSOIL, planet.getResource())
    }


}