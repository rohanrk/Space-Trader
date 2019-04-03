package com.communistutopia.spacetrader.model

import kotlin.math.pow
import kotlin.math.sqrt
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Class containing planets, tech level, and location of a Solar System
 * @property planets: All planets located in solar system
 * @property name: Name of the solar system
 * @property x: x coordinate of solar system's location
 * @property y: y coordinate of solar system's location
 * @author Rohan Rk <rohanrk@gatech.edu>
 */
@Parcelize
class SolarSystem(var planets: List<Planet>, var name: String, val x: Int,val  y: Int): Parcelable {

    var coordinate: Pair<Int, Int>

    init {
        this.planets = planets
        this.name = name
        this.coordinate = Pair(x, y)
    }

    /**
     * Function to calculate Euclidean distance between 2 Solar Systems
     * TODO: Might want to place this in Companion obj. Equivalent to making it static. Not sure yet.
     *
     * @param other solar system to compare location to
     */
    fun getDistance(other: SolarSystem): Double {
        val x_dist: Double = (this.coordinate.first - other.coordinate.first).toDouble()
        val y_dist = (this.coordinate.second - other.coordinate.second).toDouble()
        return sqrt(x_dist.pow(2) + y_dist.pow(2))
    }

    /**
     * Method Overloading. Allows Solar System to get a distance given a point rather than
     * a solar system object
     *
     * @param loc other location to compare
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