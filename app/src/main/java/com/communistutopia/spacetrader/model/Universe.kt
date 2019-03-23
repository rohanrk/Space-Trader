package com.communistutopia.spacetrader.model

import kotlin.random.Random

/**
 * Singleton object representing Game universe
 *
 * Handles all the logic for generating universe and other events
 * @author Rohan Rk
 */
object Universe {

    // Constants
    private val X_SIZE: Int = 150
    private val Y_SIZE: Int = 100
    private val NUM_SYSTEMS: Int = 10
    private var MAX_PLANETS: Int = 3

    // Objects to help generate universe and handle game events
    val solarSystems: MutableSet<SolarSystem>
    private val universe: MutableMap<Pair<Int, Int>, String> // Initializing a 2D array is the most frustrating ordeal. So this is a hacky solution
    private val random: Random
    private var namesList: MutableList<String>


    init {
        solarSystems = mutableSetOf()
        universe = mutableMapOf()
        random = Random.Default
        namesList = mutableListOf(
            "Acamar",
            "Adahn",		// The alternate personality for The Nameless One in "Planescape: Torment"
            "Aldea",
            "Andevian",
            "Antedi",
            "Balosnee",
            "Baratas",
            "Brax",			// One of the heroes in Master of Magic
            "Bretel",		// This is a Dutch device for keeping your pants up.
            "Calondia",
            "Campor",
            "Capelle",		// The city I lived in while programming this game
            "Carzon",
            "Castor",		// A Greek demi-god
            "Cestus",
            "Cheron",
            "Courteney",	// After Courteney Cox…
            "Daled",
            "Damast",
            "Davlos",
            "Deneb",
            "Deneva",
            "Devidia",
            "Draylon",
            "Drema",
            "Endor",
            "Esmee",		// One of the witches in Pratchett's Discworld
            "Exo",
            "Ferris",		// Iron
            "Festen",		// A great Scandinavian movie
            "Fourmi",		// An ant, in French
            "Frolix",		// A solar system in one of Philip K. Dick's novels
            "Gemulon",
            "Guinifer",		// One way of writing the name of king Arthur's wife
            "Hades",		// The underworld
            "Hamlet",		// From Shakespeare
            "Helena",		// Of Troy
            "Hulst",		// A Dutch plant
            "Iodine",		// An element
            "Iralius",
            "Janus",		// A seldom encountered Dutch boy's name
            "Japori",
            "Jarada",
            "Jason",		// A Greek hero
            "Kaylon",
            "Khefka",
            "Kira",			// My dog's name
            "Klaatu",		// From a classic SF movie
            "Klaestron",
            "Korma",		// An Indian sauce
            "Kravat",		// Interesting spelling of the French word for "tie"
            "Krios",
            "Laertes",		// A king in a Greek tragedy
            "Largo",
            "Lave",			// The starting system in Elite
            "Ligon",
            "Lowry",		// The name of the "hero" in Terry Gilliam's "Brazil"
            "Magrat",		// The second of the witches in Pratchett's Discworld
            "Malcoria",
            "Melina",
            "Mentar",		// The Psilon home system in Master of Orion
            "Merik",
            "Mintaka",
            "Montor",		// A city in Ultima III and Ultima VII part 2
            "Mordan",
            "Myrthe",		// The name of my daughter
            "Nelvana",
            "Nix",			// An interesting spelling of a word meaning "nothing" in Dutch
            "Nyle",			// An interesting spelling of the great river
            "Odet",
            "Og",			// The last of the witches in Pratchett's Discworld
            "Omega",		// The end of it all
            "Omphalos",		// Greek for navel
            "Orias",
            "Othello",		// From Shakespeare
            "Parade",		// This word means the same in Dutch and in English
            "Penthara",
            "Picard",		// The enigmatic captain from ST:TNG
            "Pollux",		// Brother of Castor
            "Quator",
            "Rakhar",
            "Ran",			// A film by Akira Kurosawa
            "Regulas",
            "Relva",
            "Rhymus",
            "Rochani",
            "Rubicum",		// The river Ceasar crossed to get into Rome
            "Rutia",
            "Sarpeidon",
            "Sefalla",
            "Seltrice",
            "Sigma",
            "Sol",			// That's our own solar system
            "Somari",
            "Stakoron",
            "Styris",
            "Talani",
            "Tamus",
            "Tantalos",		// A king from a Greek tragedy
            "Tanuga",
            "Tarchannen",
            "Terosa",
            "Thera",		// A seldom encountered Dutch girl's name
            "Titan",		// The largest moon of Jupiter
            "Torin",		// A hero from Master of Magic
            "Triacus",
            "Turkana",
            "Tyrus",
            "Umberlee",		// A god from AD&D, which has a prominent role in Baldur's Gate
            "Utopia",		// The ultimate goal
            "Vadera",
            "Vagra",
            "Vandor",
            "Ventax",
            "Xenon",
            "Xerxes",		// A Greek hero
            "Yew",			// A city which is in almost all of the Ultima games
            "Yojimbo",		// A film by Akira Kurosawa
            "Zalkon",
            "Zuul",		// From the first Ghostbusters movie
            "Gorgon",
            "Hydra",
            "Phoenix",
            "Gagrin",
            "Athens",
            "Knossos",
            "Macedon",
            "Thorne",
            "Sparta",
            "Dis",
            "Farinata",
            "Century",
            "Theseus",
            "Asgard"
        )
    }

    /**
     * Generates universe with constant number of solar systems. Each solar system can have one to three planets.
     * Also initializes the player's starting location on the first planet
     * @param player the player TO-DO
     */
    fun generateUniverse() {
        var i: Int = NUM_SYSTEMS
        while (i > 0) {
            val x = random.nextInt(X_SIZE)
            val y = random.nextInt(Y_SIZE)
            val num_planets = random.nextInt(MAX_PLANETS) + 1
            val pair = Pair(x, y)
            if (!universe.containsKey(pair)) {
                val planetSet = generatePlanets(num_planets)
                val systemName: String = namesList[random.nextInt(namesList.size)]
                namesList.remove(systemName)
                solarSystems.add(SolarSystem(planets = planetSet , name = systemName, x = x, y = y))
                universe[pair] = systemName
                i--
            }
        }
        // TODO: updateLocation(player) For now, I'm commenting this out till M8
    }

    /**
     * @param num_planets the number of planets to generate
     * @return planetSet, a set containing the generated planets
     */
    private fun generatePlanets(num_planets: Int): MutableSet<Planet> {
        var planetSet: MutableSet<Planet> = mutableSetOf()
        for(i in 0..num_planets) { //converted this to a for loop, might be an off by 1 error
            val planetName: String = namesList[random.nextInt(namesList.size)]
            namesList.remove(planetName)
            planetSet.add(
                Planet(
                    name = planetName,
                    techLevel = TechLevel(TechLevelType.randomTechLevelType()),
                    resourceLevel = ResourceLevel(ResourceLevelType.randomResourceLevelType()),
                    government = Government(GovernmentType.randomGovernmentType())
                )
            )
        }
        return planetSet
    }

    /**
     * This method updates a player's location. The locationPlanet is set to the first planet created in the first solar system
     * The locationSystem is set to the first system created
     */
    fun updateLocation(player: Player) {
        player.locationPlanet = solarSystems.first().planets.first()
        player.locationSystem = solarSystems.first()

    }
    override fun toString(): String {
        return solarSystems.toString() +
                '\n' + universe.toString()
    }
}
