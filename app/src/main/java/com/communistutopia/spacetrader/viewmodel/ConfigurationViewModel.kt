package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.Difficulty
import com.communistutopia.spacetrader.repository.PlayerRepository

/**
 * Viewmodel that handles player creation
 *
 * @author Rohan Rk <rohanrk@gatech.edu>
 */
class ConfigurationViewModel : ViewModel() {
    val player = PlayerRepository.player

    private val TOTAL_POINTS = 16

    /**
     * Creates player from view data
     *
     * @param difficulty selected difficulty
     * @param name player's name
     * @param pilot number of pilot points
     * @param fighter number of fighter points
     * @param trader number of trader points
     * @param engineer number of engineer points
     */
    fun updatePlayerFromView(difficulty: Difficulty, name: String, pilot: Int, fighter: Int, trader: Int, engineer: Int): Boolean {
        player.value?.difficulty = difficulty
        player.value?.charName = name
        player.value = player.value
        return updatePoints(pilot, fighter, trader, engineer)
    }

    fun updateCredits(credits: Int) {
        player.value?.credits = credits
        player.value = player.value
    }

    fun updatePoints(pilot: Int, fighter: Int, trader: Int, engineer: Int): Boolean {

        if (pilot < 0 || fighter < 0 || trader < 0 || engineer < 0 || pilot + fighter + trader + engineer != TOTAL_POINTS) {
            return false
        } else {
            player.value?.pilotSkill = pilot
            player.value?.fighterSkill = fighter
            player.value?.traderSkill = trader
            player.value?.engineerSkill = engineer
            player.value = player.value
            return true
        }
    }
}
