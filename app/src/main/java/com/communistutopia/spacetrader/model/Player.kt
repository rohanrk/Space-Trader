package com.communistutopia.spacetrader.model

data class Player(
    var difficulty: Difficulty = Difficulty.Beginner,
    var spaceship: String = "Gnat",
    var credits: Int = 1000,
    var charName: String = "",
    var pilotSkill: Int = 0,
    var fighterSkill: Int = 0,
    var traderSkill: Int = 0,
    var engineerSkill: Int = 0)

enum class Difficulty {
	Beginner, Easy, Normal, Hard, Impossible
}
