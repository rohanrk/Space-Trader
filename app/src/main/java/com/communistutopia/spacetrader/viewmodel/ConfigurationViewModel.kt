package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.Player

class ConfigurationViewModel : ViewModel() {

    private val player: Player
    private val TOTAL_POINTS = 16

    // TODO: Implement the ViewModel
    init {
        player = Player()
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
