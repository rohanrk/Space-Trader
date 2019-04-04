package com.communistutopia.spacetrader.model

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Class containing planets, tech level, and location of a Solar System
 * @property planets: All planets located in solar system
 * @property name: Name of the solar system
 * @property x: x coordinate of solar system's location
 * @property y: y coordinate of solar system's location
 * @author Rohan Rk <rohanrk@gatech.edu>
 */
class SolarSystem(var planets: List<Planet>, var name: String, val x: Int,val  y: Int) {

    // Blank constructor for Firebase
    constructor(): this(ArrayList<Planet>(), "", 0, 0)

    /**
     * Function to calculate Euclidean distance between 2 Solar Systems
     * TODO: Might want to place this in Companion obj. Equivalent to making it static. Not sure yet.
     *
     * @param other solar system to compare location to
     */
    fun getDistance(other: SolarSystem): Double {
        val x_dist: Double = (x - other.x).toDouble()
        val y_dist = (y - other.y).toDouble()
        return sqrt(x_dist.pow(2) + y_dist.pow(2)) + 10
    }

    /**
     * Method Overloading. Allows Solar System to get a distance given a point rather than
     * a solar system object
     *
     * @param loc other location to compare
     */
    fun getDistance(loc: Pair<Int, Int>): Double {
        val x_dist: Double = (x - loc.first).toDouble()
        val y_dist = (y - loc.second).toDouble()
        return sqrt(x_dist.pow(2) + y_dist.pow(2))
    }

    override fun toString(): String {
        return "SolarSystem(" + "\n" +
                "name='$name', " + "\n" +
                "planets=$planets, " + "\n" +
                ")"
    }
}