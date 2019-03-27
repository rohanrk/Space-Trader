package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.Planet
import com.communistutopia.spacetrader.model.Player
import com.communistutopia.spacetrader.model.SolarSystem
import com.communistutopia.spacetrader.model.Universe

/**
 * Viewmodel that manages changing solar systems
 *
 * @author Rohan Rk <rohanrk@gatech.edu>
 */
class LocationViewModel: ViewModel() {

    lateinit var player: Player

    /**
     * Changes the location of the player and calls the method to lower fuel if travel is possible
     *
     */
    fun travelToPlanet(destinationSystem: SolarSystem, destinationPlanet: Planet) {
        if(player.spaceship.canTravelTo(player.system, destinationSystem)) {
            var dist = player.system.getDistance(destinationSystem)
            updateLocation(destinationSystem, destinationPlanet)
            player.spaceship.updateFuelForTravel(dist)
        }
    }

    /**
     * Returns a set of the systems reachable by the player
     *
     */
    fun findReachableSystems(): MutableSet<SolarSystem> {
        val reachableSolarSystems: MutableSet<SolarSystem> = mutableSetOf()
        for(system: SolarSystem in Universe.solarSystems) {
            if(player.system != system && player.spaceship.canTravelTo(player.system, system)) {
                reachableSolarSystems.add(system);
            }
        }
        return reachableSolarSystems
    }

    /**
     * This method updates a player's location fields
     */
    fun updateLocation(solarSystem: SolarSystem, planet: Planet) {
        player.location = planet
        player.system = solarSystem
    }

}