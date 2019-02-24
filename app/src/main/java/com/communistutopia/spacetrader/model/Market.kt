package com.communistutopia.spacetrader.model

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
class Market(private val techLevel: TechLevel, private val resourceLevel: ResourceLevel,
             private val government: Government) {

}