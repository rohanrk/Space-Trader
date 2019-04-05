package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.media.MediaPlayer
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.repository.PlayerRepository

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
