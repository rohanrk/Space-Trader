package com.communistutopia.spacetrader.model

data class Player(
	val difficulty: Difficulty? = Difficulty.Beginner,
	val spaceship: String? = "Gnat",
	val credits: Int? = 1000,
	val characterName: String? = "",
	val pilotSkill: Int? = 0,
	val fighterSkill: Int? = 0,
	val traderSkill: Int? = 0,
	val engineerSkill: Int? = 0
)

enum class Difficulty {
	Beginner, Easy, Normal, Hard, Impossible
}
