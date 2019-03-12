package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.Difficulty
import com.communistutopia.spacetrader.model.Player

class ConfigurationViewModel : ViewModel() {

    val player: Player = Player()
    private val TOTAL_POINTS = 16

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
