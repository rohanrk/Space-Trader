package com.communistutopia.spacetrader.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

/**
 * @param resourceLevel a ResourceLevelType that will probably need to change
 *      stubbing this out for now because I have very many questions about it
 *
 * Initialization is done by the constructor, so all of these parameters are immutable properties
 *  of an instance of a ResourceLevel once it is created
 */
@Parcelize
class ResourceLevel(val resourceLevel: ResourceLevelType): Parcelable {
    override fun toString(): String {
        return "ResourceLevel(resourceLevel=$resourceLevel)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResourceLevel

        if (resourceLevel != other.resourceLevel) return false

        return true
    }

    override fun hashCode(): Int {
        return resourceLevel.hashCode()
    }

}

enum class ResourceLevelType {

    NOSPECIALRESOURCES, MINERALRICH, MINERALPOOR, DESERT, LOTSOFWATER, RICHSOIL, POORSOIL, RICHFAUNA, LIFELESS,
    WEIRDMUSHROOMS, LOTSOFHERBS, ARTISTIC, WARLIKE;

    companion object {
        /**
         * @returns a random ResourceLevelType
         */
        fun randomResourceLevelType(): ResourceLevelType {
            val values = ResourceLevelType.values()
            val random: Random = Random.Default
            val pick = random.nextInt(values.size)
            return values[pick]
        }
    }
}