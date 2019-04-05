package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.communistutopia.spacetrader.model.Difficulty
import com.communistutopia.spacetrader.model.Player
import com.communistutopia.spacetrader.repository.PlayerRepository
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Viewmodel that handles player creation
 *
 * @author Rohan Rk <rohanrk@gatech.edu>
 */
class ConfigurationViewModel : ViewModel() {
    val player = Player()

    private val totalPoints = 16

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
    fun updatePlayerFromView(difficulty: Difficulty, name: String, pilot: Int, fighter: Int, trader: Int,
                             engineer: Int): Boolean {
        player.difficulty = difficulty
        player.charName = name
        return updatePoints(pilot, fighter, trader, engineer)
    }

    fun updateCredits(credits: Int) {
        player.credits = credits
    }

    private fun updatePoints(pilot: Int, fighter: Int, trader: Int, engineer: Int): Boolean {

        return if (pilot < 0 || fighter < 0 || trader < 0 || engineer < 0 ||
            pilot + fighter + trader + engineer != totalPoints) {
            false
        } else {
            player.pilotSkill = pilot
            player.fighterSkill = fighter
            player.traderSkill = trader
            player.engineerSkill = engineer
            true
        }
    }

    fun generateAndSavePlayer() {
        player.firstTimeInit()
        // save object to firebase
        val db = FirebaseFirestore.getInstance()
        db.collection("player").add(this)
            .addOnSuccessListener { documentReference ->
                PlayerRepository.documentId = documentReference.id
                PlayerRepository.player.value = player
            }
            .addOnFailureListener { e -> Log.w("YEET", "Error writing document", e) }
    }
}
