package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.SolarSystem
import com.communistutopia.spacetrader.model.Universe
import android.media.MediaPlayer
import android.content.Context
import com.communistutopia.spacetrader.R


/**
 * Main game screen
 * @author Drake Witt <dwitt@dranweb.com>
 */

class GameViewModel : ViewModel() {
    private val solarSystems: HashSet<SolarSystem>

    init {
        Universe.generateUniverse()
        solarSystems = Universe.solarSystems
    }

    fun goToSpace(context: Context?) {
        print("WARP FACTOR 9")
        // WARP FACTOR 9
        val mp = MediaPlayer.create(context, R.raw.nasa)
        mp.setOnPreparedListener {
            println("Wesley Snipes is coming, GO GO GO GO!")
            mp.start()
        }
    }
}
