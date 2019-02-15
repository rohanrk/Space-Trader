package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.Difficulty
import com.communistutopia.spacetrader.model.Player

class ConfigurationViewModel : ViewModel() {

    private val player: Player
    private val TOTAL_POINTS = 16

    // TODO: Refactor ViewModel based on design principles
    init {
        player = Player()
    }

    fun updatePlayerFromView(difficulty: Difficulty, name: String, pilot: Int, fighter: Int, trader: Int, engineer: Int) {
        player.difficulty = difficulty
        player.charName = name
        updatePoints(pilot, fighter, trader, engineer)
    }

    fun updateCredits(credits: Int) {
        player.credits = credits
    }

    fun updatePoints(pilot: Int, fighter: Int, trader: Int, engineer: Int) {
        if (pilot + fighter + trader + engineer > TOTAL_POINTS) {
            // TODO: Error handling. Max points exceed 16
        } else {
            player.pilotSkill = pilot
            player.fighterSkill = fighter
            player.traderSkill = trader
            player.engineerSkill = engineer
        }
    }
}
