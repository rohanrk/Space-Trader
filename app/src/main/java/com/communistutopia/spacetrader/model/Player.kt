package com.communistutopia.spacetrader.model

import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random

/**
 * This class represents a player with default values.
 *@param difficulty difficulty of game
 * @param spaceship player's ship, default ship is a gnat
 * @param locationPlanet player initial planet, updated to a real planet after the universe is created
 * @param locationSystem player initial system, updated to a real system after the universe is created
 * @param credits players starting money
 * @param charName name
 * @param pilotSkill points attributed to the pilot category
 * @param fighterSkill points attributed to the fighter category
 * @param traderSkill points attributed to the trader category
 * @param engineerSkill points attributed to the engineer category
 */
data class Player(
    var difficulty: Difficulty = Difficulty.Beginner,
    var spaceship: Ship = Ship(),
    var credits: Int = 1000,
    var charName: String = "",
    var pilotSkill: Int = 0,
    var fighterSkill: Int = 0,
    var traderSkill: Int = 0,
    var engineerSkill: Int = 0) {

    lateinit var system: SolarSystem
    lateinit var solarSystems: List<SolarSystem>
    lateinit var location: Planet
    lateinit var uid: String

    /**
     * Sets the start location randomly
     */
    fun firstTimeInit() {
        Universe.generateUniverse()
        solarSystems = Universe.solarSystems.toList()
        val random: Random = Random.Default
        val solarIndex = random.nextInt(solarSystems.size)
        this.system = solarSystems.elementAt(solarIndex)
        val planetIndex = random.nextInt(system.planets.size)
        this.location = system.planets.elementAt(planetIndex)

        // get current user
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        this.uid = auth.uid!!
    }

    fun removeHalfOfCredits() {
        credits /= 2
    }

}


enum class Difficulty {
	Beginner, Easy, Normal, Hard, Impossible
}
