package com.communistutopia.spacetrader.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * This class represents a player's ship.
 * @param hold a ship's cargohold
 * @param name the type of a ship
 * @param fuelCapacity amount of fuel a ship can hold
 * @param fuelCount amount of fuel a ship currently has
 * @param hullStrength current health of ship
 * @param hasInsurance if a ship is covered by insurance
 * @param hasEscapePods if there is an available escape pod on the ship
 * @param range maximum distance a ship can travel on a full tank of fuel
 * @param weapons list of weapons the ship has equipped
 * @param shields list of shields the ship has equipped
 * @param gadgets list of gadgets the ship has equipped
 * @param cargoCapacity the maximum amount of cargo items a ship can hold
 * @param weaponSlots the number of weapons a ship can have
 * @param shieldSlots the number of shields a ship can have
 * @param gadgetSlots the number of gadgets a ship can have
 * @param crewQuarters the number of crew members a ship can have
 *
 */
@Parcelize
data class Ship(
    val hold: Inventory, val name: String, val fuelCapacity: Int, var fuelCount: Int, val hullStrength: Int,
    val hasInsurance: Boolean, val hasEscapePods: Boolean, val range: Int, val weapons: List<Weapon>,
    val shields: List<Shield>, val gadgets: List<Gadgets>, var cargoCapacity: Int, val weaponSlots: Int,
    val shieldSlots: Int, val gadgetSlots: Int,
    val crewQuarters: Int): Parcelable {

    /**
     * Returns whether the ship has enough fuel / range to get to a system
     *
     */
    fun canTravelTo(source: SolarSystem, destinationSystem: SolarSystem): Boolean {
        var dist = source.getDistance(destinationSystem)
        var rangeLeft = (fuelCount / fuelCapacity) * range
        if(dist <= rangeLeft) {
            return true
        }
        return false
    }

    /**
     * Lowers the fuelCount of the ship for traveling a specified distance
     *
     */
    fun updateFuelForTravel(dist: Double) {
        fuelCount -= ((dist / range) * fuelCapacity).toInt()
    }
}

enum class Weapon {
    PULSE_LASER, BEAM_LASER, MILITARY_LASER, NONE
}

enum class Shield {
    ENERGY, REFLECTIVE, NONE
}

enum class Gadgets {
    EXTRA_CARGO_BAYS, NAVIGATING_SYSTEM, AUTO_REPAIR, TARGETING_SYSTEM, CLOAKING_DEVICE, NONE
}