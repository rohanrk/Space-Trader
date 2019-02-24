package com.communistutopia.spacetrader.model

import kotlin.random.Random

/**
 * @param resourceLevel a ResourceLevelType that will probably need to change
 *      stubbing this out for now because I have very many questions about it
 *
 * Initialization is done by the constructor, so all of these parameters are immutable properties
 *  of an instance of a ResourceLevel once it is created
 */
class ResourceLevel(private val resourceLevel: ResourceLevelType)

enum class ResourceLevelType {

    NOSPECIALRESOURCES, MINERALRICH, MINERALPOOR, DESERT, LOTSOFWATER, RICHSOIL, POORSOIL, RICHFAUNA, LIFELESS,
    WEIRDMUSHROOMS, LOTSOFHERBS, ARTISTIC, WARLIKE;
    companion object {
        fun randomResourceLevelType(): ResourceLevelType {
            val values = ResourceLevelType.values()
            val random: Random = Random.Default
            val pick = random.nextInt(values.size)
            return values[pick]
        }
    }
}