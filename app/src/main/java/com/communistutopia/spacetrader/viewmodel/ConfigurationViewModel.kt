package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import android.widget.Toast
import com.communistutopia.spacetrader.model.Difficulty
import com.communistutopia.spacetrader.model.Player

class ConfigurationViewModel : ViewModel() {

    val player: Player
    private val TOTAL_POINTS = 16

    // TODO: Refactor ViewModel based on design principles
    init {
        player = Player()
    }

    fun updatePlayerFromView(difficulty: Difficulty, name: String, pilot: Int, fighter: Int, trader: Int, engineer: Int): Boolean {
        player.difficulty = difficulty
        player.charName = name
        return updatePoints(pilot, fighter, trader, engineer)
    }

    fun updateCredits(credits: Int) {
        player.credits = credits
    }

    fun updatePoints(pilot: Int, fighter: Int, trader: Int, engineer: Int): Boolean {

        if (pilot < 0 || fighter < 0 || trader < 0 || engineer < 0 || pilot + fighter + trader + engineer != TOTAL_POINTS) {
            // TODO: Error handling. Max points exceed 16
            return false
        } else {
            player.pilotSkill = pilot
            player.fighterSkill = fighter
            player.traderSkill = trader
            player.engineerSkill = engineer
            return true
        }
    }
}
