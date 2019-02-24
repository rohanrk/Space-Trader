package com.communistutopia.spacetrader.model

import kotlin.random.Random
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Singleton object that handles all the logic for generating universe and other events
 */
object Universe {

    // Constants
    private val X_SIZE: Int = 150
    private val Y_SIZE: Int = 100
    private val NUM_SYSTEMS: Int = 10

    private val solarSystems: ArrayList<SolarSystem> = ArrayList()
    private val universe: HashMap<Pair<Int, Int>, String> // Initializing a 2D array is the most frustrating ordeal. So this is a hacky solution
    private val random: Random


    init {
        universe = HashMap()
        random = Random.Default
    }

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
            }
        }
    }
}

class SolarSystem(planet: Planet, name: String, tech: Int = 0, x: Int, y: Int) {

    var planet: Planet
    var name: String
    var tech: Int
    var coordinate: Pair<Int, Int>

    init {
        this.planet = planet
        this.name = name
        this.tech = tech
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

}

class Planet(name: String, hasPort: Boolean = true) {

    var name: String
    var hasSpacePort: Boolean

    init {
        this.name = name
        this.hasSpacePort = hasPort
    }

}