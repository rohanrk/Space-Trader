package com.communistutopia.spacetrader.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @param techLevel a TechLevel object
 * @param resourceLevel a ResourceLevel object
 * @param government a Government object
 *
 * All of these params will be used to calculate the relative cost of goods on a planet
 *
 * Initialization is done by the constructor, so all of these parameters are immutable properties
 *  of an instance of a Market once it is created
 */
@Parcelize
class Market(val techLevel: TechLevel, val resourceLevel: ResourceLevel,
             val government: Government): Parcelable {

    var event: Event
    var inventory: Inventory

    init {
        event = Event.None
        inventory = Inventory()
    }
    constructor(techLevel: TechLevel, resourceLevel: ResourceLevel,
                government: Government, event: Event) : this(techLevel, resourceLevel, government) {
        this.event = event

    }

    override fun toString(): String {
        return "Market(" + "\n" +
                "techLevel=$techLevel, " + "\n" +
                "resourceLevel=$resourceLevel, " + "\n" +
                "government=$government" + "\n" +
                ")"
    }

}