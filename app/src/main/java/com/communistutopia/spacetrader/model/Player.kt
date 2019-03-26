package com.communistutopia.spacetrader.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

/**
 * This class represents a player with default values.
 *@param difficulty difficulty of game
 * @param spaceship player's ship, default ship is a gnat
 * @param location player initial location, updated to a real planet after the universe is created
 * @param credits players starting money
 * @param charName name
 * @param pilotSkill points attributed to the pilot category
 * @param fighterSkill points attributed to the fighter category
 * @param traderSkill points attributed to the trader category
 * @param engineerSkill points attributed to the engineer category
 */
@Parcelize
data class Player(
    var difficulty: Difficulty = Difficulty.Beginner,
    var spaceship: Ship = Ship(Inventory(),
        "Gnat", 0,100, false, false, 14, listOf(Weapon.NONE), listOf(Shield.NONE),
        listOf(Gadgets.NONE), 15, 1, 0, 1, 1),
    var credits: Int = 1000,
    var charName: String = "",
    var pilotSkill: Int = 0,
    var fighterSkill: Int = 0,
    var traderSkill: Int = 0,
    var engineerSkill: Int = 0): Parcelable {

    lateinit var location: Planet

    fun generateStartLoc(solarSystems: Set<SolarSystem>) {
        var random: Random = Random.Default
        val solarIndex = random.nextInt(solarSystems.size)
        val system = solarSystems.elementAt(solarIndex)
        val planetIndex = random.nextInt(system.planets.size)
        this.location = system.planets.elementAt(random.nextInt(planetIndex))
    }
}


enum class Difficulty {
	Beginner, Easy, Normal, Hard, Impossible
}
