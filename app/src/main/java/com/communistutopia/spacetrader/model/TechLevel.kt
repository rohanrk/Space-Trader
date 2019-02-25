package com.communistutopia.spacetrader.model

import kotlin.random.Random

/**
 * @param techLevel a TechLevelType representing the technological
 *  advancedness of a planet. Will be used to assess the value of goods later on
 *
 *  Initialization is done by the constructor, so all of these parameters are immutable properties
 *  of an instance of a Planet once it is created
 */
class TechLevel(private val techLevel: TechLevelType) {
    override fun toString(): String {
        return "TechLevel(techLevel=$techLevel)"
    }
}

enum class TechLevelType {
    PreAgricultural, Agricultural, Medieval, Renaissance, EarlyIndustrial, Industrial, PostIndustrial, HiTech;

    companion object {
        /**
         * @returns a random TechLevelType
         */
        fun randomTechLevelType(): TechLevelType {
            val values = TechLevelType.values()
            val random: Random = Random.Default
            val pick = random.nextInt(values.size)
            return values[pick]
        }
    }
}