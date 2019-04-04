package com.communistutopia.spacetrader.model

import kotlin.random.Random

/**
 * @param governmentType a GovernmentType that we will use to do stuff and things later
 *
 * Initialization is done by the constructor, so all of these parameters are immutable properties
 *  of an instance of a Government once it is created
 */
class Government(val governmentType: GovernmentType) {

    constructor(): this(GovernmentType.Anarchy)

    override fun toString(): String {
        return "Government(governmentType=$governmentType)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Government

        if (governmentType != other.governmentType) return false

        return true
    }

    override fun hashCode(): Int {
        return governmentType.hashCode()
    }
}

enum class GovernmentType {
    Anarchy, CapitalistState, CommunistState, Confederacy, CorporateState, CyberneticState, Democracy, Dictatorship,
    FascistState, FeudalState, MilitaryState, Monarchy, PacifistState, SocialistState, StateOfSatori, Technocracy,
    Theocracy;

    companion object {
        /**
         * @returns a random GovernmentType
         */
        fun randomGovernmentType(): GovernmentType {
            val values = GovernmentType.values()
            val random: Random = Random.Default
            val pick = random.nextInt(values.size)
            return values[pick]
        }
    }
}