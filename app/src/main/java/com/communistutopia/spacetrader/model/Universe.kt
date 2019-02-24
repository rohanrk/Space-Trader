package com.communistutopia.spacetrader.model

import kotlin.random.Random
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Singleton object representing Game universe
 *
 * Handles all the logic for generating universe and other events
 * @author Rohan Rk
 */
object Universe {

    // Constants
    private val X_SIZE: Int = 150
    private val Y_SIZE: Int = 100
    private val NUM_SYSTEMS: Int = 10

    // Objects to help generate universe and handle game events
    private val solarSystems: HashSet<SolarSystem>
    private val universe: HashMap<Pair<Int, Int>, String> // Initializing a 2D array is the most frustrating ordeal. So this is a hacky solution
    private val random: Random


    init {
        solarSystems = hashSetOf()
        universe = hashMapOf()
        random = Random.Default
    }

    /**
     * Generates universe with constant number of solar systems.
     *
     * TODO: Right now, each solar system is initialized with the name 'System1', 'System2'... We will want to generate a random list of solar system and planet names to create our universe in the future.
     */
    fun generateUniverse() {
        var i: Int = NUM_SYSTEMS
        while (i > 0) {
            var x = random.nextInt(X_SIZE)
            var y = random.nextInt(Y_SIZE)
            val pair = Pair(x, y)
            if (!universe.containsKey(pair)) {
                var name: String = "System%d".format(i)
                solarSystems.add(SolarSystem(planet = Planet(""), name = name, x = x, y = y))
                universe[pair] = name
                i--
            }
        }
    }
}

/**
 * Class containing planets, tech level, and location of a Solar System
 * @property planets: All planets located in solar system
 * @property name: Name of the solar system
 * @property x: x coordinate of solar system's location
 * @property y: y coordinate of solar system's location
 * @author Rohan Rk
 */
class SolarSystem(planet: Planet, name: String, x: Int, y: Int) {

    var planets: Set<Planet>
    var name: String
    var coordinate: Pair<Int, Int>

    init {
        this.planets = setOf(planet)
        this.name = name
        this.coordinate = Pair(x, y)
    }

    /**
     * Function to calculate Euclidean distance between 2 Solar Systems
     * TODO: Might want to place this in Companion obj. Equivalent to making it static. Not sure yet.
     */
    fun getDistance(other: SolarSystem): Double {
        val x_dist: Double = (this.coordinate.first - other.coordinate.first) as Double
        val y_dist = (this.coordinate.second - other.coordinate.second) as Double
        return sqrt(x_dist.pow(2) + y_dist.pow(2))
    }

    // TODO: Solar system should be able to add planets when we generate more complex universes

}

/**
 * Basic Planet class
 *
 * @property name: The name of the planet
 * @property tech: Technology level of the planet. Defaulted to 0
 * @property hasPort: Whether the planet has a space port or not. Default is true
 * @author Rohan Rk
 */
class Planet(name: String, tech: Int = 0, hasPort: Boolean = true) {

    var name: String
    var tech: Int
    var hasSpacePort: Boolean // Default is true, but we could add random logic or make it so only one planet has a spaceport

    init {
        this.name = name
        this.tech = tech
        this.hasSpacePort = hasPort
    }

}