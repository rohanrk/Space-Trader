package com.communistutopia.spacetrader.model

import android.os.Parcelable

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
//TODO: Implement Parcelable so we can pass Player data between views
data class Player(
    var difficulty: Difficulty = Difficulty.Beginner,
    var spaceship: Ship = Ship(Inventory(0,0,0,0,0,0,0,0,0,0),
        "Gnat", 0, 0,100, false, false, 14, listOf(Weapon.NONE), listOf(Shield.NONE),
        listOf(Gadgets.NONE), 15, 1, 0, 1, 1),
    var locationPlanet: Planet =
    Planet(name = "Initial",
        techLevel = TechLevel(TechLevelType.randomTechLevelType()),
        resourceLevel = ResourceLevel(ResourceLevelType.randomResourceLevelType()),
        government = Government(GovernmentType.randomGovernmentType())),
    var locationSystem: SolarSystem,
    var credits: Int = 1000,
    var charName: String = "",
    var pilotSkill: Int = 0,
    var fighterSkill: Int = 0,
    var traderSkill: Int = 0,
    var engineerSkill: Int = 0) {

    fun availableCargo(): Int {
        return spaceship.cargoCapacity
    }

    fun travelToPlanet(destinationSystem: SolarSystem, destinationPlanet: Planet) {
        if(spaceship.canTravelTo(this, destinationSystem)) {
            locationSystem = destinationSystem
            locationPlanet = destinationPlanet
            spaceship.updateFuelForTravel(this, destinationSystem)
        } else {
            //TODO let the player know they cannot travel
        }
    }

}


enum class Difficulty {
	Beginner, Easy, Normal, Hard, Impossible
}
