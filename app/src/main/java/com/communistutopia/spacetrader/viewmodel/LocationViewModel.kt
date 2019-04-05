package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.Planet
import com.communistutopia.spacetrader.model.SingleLiveEvent
import com.communistutopia.spacetrader.model.SolarSystem
import com.communistutopia.spacetrader.repository.PlayerRepository

/**
 * Viewmodel that manages changing solar systems
 *
 * @author Rohan Rk <rohanrk@gatech.edu>
 */
class LocationViewModel: ViewModel() {
    val player = PlayerRepository.player

    private val _pirateEvent = SingleLiveEvent<Any>()
    private val _policeEvent = SingleLiveEvent<Any>()

    val pirateEvent: LiveData<Any>
        get() = _pirateEvent
    val policeEvent: LiveData<Any>
        get() = _policeEvent

    /**
     * Changes the location of the player and calls the method to lower fuel if travel is possible
     */
    fun travelToPlanet(destinationSystem: SolarSystem, destinationPlanet: Planet) {
        if(player.value!!.spaceship.canTravelTo(player.value!!.system, destinationSystem)) {
            val dist = player.value!!.system.getDistance(destinationSystem)
            println("DIST: $dist")
            updateLocation(destinationSystem, destinationPlanet)
            player.value!!.location.rollForRandomEvent()
            val pirates = player.value!!.location.rollForPirates()
            if (pirates) {
                player.value!!.spaceship.hold.removeHalfOfCargo()
                _pirateEvent.call()
            }
            val police = player.value!!.location.rollForPolice()
            if (police && !pirates) {
                player.value!!.spaceship.hold.removeHalfOfCargo()
                player.value!!.removeHalfOfCredits()
                _policeEvent.call()
            }
            player.value!!.spaceship.updateFuelForTravel(dist)
            player.value = player.value
        }
    }

    /**
     * Returns a set of the systems reachable by the player
     */
    private fun findReachableSystems(): MutableSet<SolarSystem> {
        val reachableSolarSystems: MutableSet<SolarSystem> = mutableSetOf()
        for(system: SolarSystem in player.value!!.solarSystems) {
            if(player.value!!.system != system && player.value!!.spaceship.canTravelTo(player.value!!.system, system)) {
                reachableSolarSystems.add(system)
            }
        }
        return reachableSolarSystems
    }

    /**
     * Data class for the spinner to read that contains the planet, solar system, and title for the spinner.
     */
    data class TravelSpinnerEntry(val planet: Planet, val solarSystem: SolarSystem, val title: String) {
        override fun toString(): String {
            return title
        }
    }

    fun getAllReachablePlanets(): MutableSet<TravelSpinnerEntry> {
        val reachablePlanets: MutableSet<TravelSpinnerEntry> = mutableSetOf()
        val reachableSystems = findReachableSystems()
        reachableSystems.forEach { system ->
            system.planets.forEach { planet ->
                if (planet.name != player.value!!.location.name) {
                    reachablePlanets.add(TravelSpinnerEntry(planet, system, "${system.name} - ${planet.name}"))
                }
            }
        }
        return reachablePlanets
    }

    /**
     * This method updates a player's location fields
     */
    private fun updateLocation(solarSystem: SolarSystem, planet: Planet) {
        player.value!!.location = planet
        player.value!!.system = solarSystem
        player.value = player.value
    }

}