package com.communistutopia.spacetrader.model

import kotlin.random.Random

/**
 * @param name the name of the Planet
 * @param techLevel the TechLevel of the Planet
 * @param resourceLevel the ResourceLevel of the Planet
 * @param government the Government level of the Planet
 *
 * Initialization is done by the constructor, so all of these parameters are immutable properties
 *  of an instance of a Planet once it is created
 */

class Planet(val name: String, val techLevel: TechLevel, val resourceLevel: ResourceLevel,
             val government: Government) {

    constructor(): this("", TechLevel(TechLevelType.HiTech), ResourceLevel(ResourceLevelType.NOSPECIALRESOURCES),
        Government(GovernmentType.Anarchy))

    //Initialize the market of the planet from it's properties
    val market: Market = Market(techLevel, resourceLevel, government)

    fun rollForRandomEvent(): Event {
        val randomEventNumber = Random.nextInt(0, Event.values().size)
        market.event = Event.values()[randomEventNumber]
        return Event.values()[randomEventNumber]
    }

    fun rollForPirates(): Boolean {
        var chance = Random.nextInt(20)
        when {
            government.governmentType == GovernmentType.Anarchy -> chance += 3
            government.governmentType == GovernmentType.Confederacy -> chance += 2
            government.governmentType == GovernmentType.Monarchy -> chance += 1
        }
        return chance > 19
    }

    fun rollForPolice(): Boolean {
        var chance = Random.nextInt(20)
        when {
            government.governmentType == GovernmentType.Dictatorship -> chance += 4
            government.governmentType == GovernmentType.FascistState -> chance += 3
            government.governmentType == GovernmentType.MilitaryState -> chance += 2
            government.governmentType == GovernmentType.Theocracy -> chance += 1
        }
        return chance > 19
    }

    override fun toString(): String {
        return "Planet(" + "\n" +
                "name='$name', " + "\n" +
                "techLevel=$techLevel, " + "\n" +
                "resourceLevel=$resourceLevel, " + "\n" +
                "government=$government, " + "\n" +
                "market=$market" + "\n" +
                ")"
    }
}