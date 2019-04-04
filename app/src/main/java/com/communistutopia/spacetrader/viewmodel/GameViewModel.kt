package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.SolarSystem
import com.communistutopia.spacetrader.model.Universe
import android.media.MediaPlayer
import android.content.Context
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.repository.PlayerRepository
import kotlin.random.Random
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Main game screen
 * @author Drake Witt <dwitt@dranweb.com>
 */

class GameViewModel : ViewModel() {
    val player = PlayerRepository.player

    fun goToSpace(context: Context?) {
        // WARP FACTOR 9
        val mp = MediaPlayer.create(context, R.raw.nasa)
        mp.setOnPreparedListener {
            println("Wesley Snipes is coming, GO GO GO GO!")
            mp.start()
        }
    }
}
