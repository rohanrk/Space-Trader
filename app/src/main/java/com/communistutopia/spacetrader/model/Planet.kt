package com.communistutopia.spacetrader.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
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
@Parcelize
class Planet(val name: String, private val techLevel: TechLevel, private val resourceLevel: ResourceLevel,
             private val government: Government): Parcelable {

    //Initialize the market of the planet from it's properties
    val market: Market = Market(techLevel, resourceLevel, government)

    fun rollForRandomEvent(): Event {
        val randomEventNumber = Random.nextInt(0, Event.values().size)
        market.event = Event.values()[randomEventNumber]
        return Event.values()[randomEventNumber]
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