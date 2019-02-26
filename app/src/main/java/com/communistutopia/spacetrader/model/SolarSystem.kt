package com.communistutopia.spacetrader.model

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Class containing planets, tech level, and location of a Solar System
 * @property planets: All planets located in solar system
 * @property name: Name of the solar system
 * @property x: x coordinate of solar system's location
 * @property y: y coordinate of solar system's location
 * @author Rohan Rk
 */
class SolarSystem(planets: Set<Planet>, name: String, x: Int, y: Int) {

    var planets: Set<Planet>
    var name: String
    var coordinate: Pair<Int, Int>

    init {
        this.planets = planets
        this.name = name
        this.coordinate = Pair(x, y)
    }

    /**
     * Function to calculate Euclidean distance between 2 Solar Systems
     * TODO: Might want to place this in Companion obj. Equivalent to making it static. Not sure yet.
     */
    fun getDistance(other: SolarSystem): Double {
        val x_dist: Double = (this.coordinate.first - other.coordinate.first).toDouble()
        val y_dist = (this.coordinate.second - other.coordinate.second).toDouble()
        return sqrt(x_dist.pow(2) + y_dist.pow(2))
    }

    /**
     * Method Overloading. Allows Solar System to get a distance given a point rather than
     * a solar system object
     */
    fun getDistance(loc: Pair<Int, Int>): Double {
        val x_dist: Double = (this.coordinate.first - loc.first).toDouble()
        val y_dist = (this.coordinate.second - loc.second).toDouble()
        return sqrt(x_dist.pow(2) + y_dist.pow(2))
    }

    override fun toString(): String {
        return "SolarSystem(" + "\n" +
                "name='$name', " + "\n" +
                "coordinate=$coordinate" + "\n" +
                "planets=$planets, " + "\n" +
                ")"
    }


}