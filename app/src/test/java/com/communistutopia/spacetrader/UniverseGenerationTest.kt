package com.communistutopia.spacetrader

import com.communistutopia.spacetrader.model.SolarSystem
import com.communistutopia.spacetrader.model.Universe

import org.junit.BeforeClass
import org.junit.Test

class UniverseGenerationTest {

    companion object {

        private const val NUM_SYSTEMS = 10
        private const val NUM_PLANETS = 3 * NUM_SYSTEMS
        private lateinit var systems: Set<SolarSystem>
        private var names: MutableSet<String> = mutableSetOf()

        @BeforeClass @JvmStatic
        fun generate() {
            Universe.generateUniverse()
            systems = Universe.solarSystems
        }
    }

    /**
     * Solar System and Planet tests during Universe Generation
     */
    @Test
    fun testSystems() {
        // Make sure we have the correct number of systems
        assert(systems.size == NUM_SYSTEMS)
        for (system in systems) {
            // Check for duplicate names
            assert(!names.contains(system.name))
            names.add(system.name)
            for (other in systems) {
                if (system != other) {
                    // Check for duplicate coordinates
                    assert(system.x != other.x || system.y != other.y)
                }
            }
        }
    }

    /**
     * Planet tests during Universe Generation
     */
    @Test
    fun testPlanets() {
        var sum = 0
        for (system in systems) {
            sum += system.planets.size
            for (planet in system.planets) {
                // Make sure we don't have duplicate names
                assert(!names.contains(planet.name))
                names.add(planet.name)
            }
        }
        // We can have a max of 30 planets
        assert(sum <= NUM_PLANETS)
    }
}