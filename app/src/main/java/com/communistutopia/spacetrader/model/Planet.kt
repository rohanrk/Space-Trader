package com.communistutopia.spacetrader.model

/**
 * @param name the name of the Planet
 * @param techLevel the TechLevel of the Planet
 * @param resourceLevel the ResourceLevel of the Planet
 * @param government the Government level of the Planet
 *
 * Initialization is done by the constructor, so all of these parameters are immutable properties
 *  of an instance of a Planet once it is created
 */
class Planet(private val name: String, private val techLevel: TechLevel, private val resourceLevel: ResourceLevel,
             private val government: Government) {

    //Initialize the market of the planet from it's properties
    private val market: Market = Market(techLevel, resourceLevel, government)

}