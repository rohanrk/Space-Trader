package com.communistutopia.spacetrader.model

/**
 * @param governmentType a GovernmentType that we will use to do stuff and things later
 *
 * Initialization is done by the constructor, so all of these parameters are immutable properties
 *  of an instance of a Government once it is created
 */
class Government(private val governmentType: GovernmentType){

}

enum class GovernmentType {
    Anarchy, CapitalistState, CommunistState, Confederacy, CorporateState, CyberneticState, Democracy, Dictatorship,
    FascistState, FeudalState, MilitaryState, Monarchy, PacifistState, SocialistState, StateOfSatori, Technocracy,
    Theocracy
}