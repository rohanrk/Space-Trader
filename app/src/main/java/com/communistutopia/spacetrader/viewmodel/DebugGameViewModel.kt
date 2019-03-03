package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.Player
import com.communistutopia.spacetrader.model.SolarSystem
import com.communistutopia.spacetrader.model.Universe

/**
 * Debug class to display solar system and planet information.
 * Used for demoing M6
 * @author Drake Witt <dwitt@dranweb.com>
 */

class DebugGameViewModel : ViewModel() {
    val player: Player = Player()
    val solarSystems: HashSet<SolarSystem>

    init {
        Universe.generateUniverse(player)
        solarSystems = Universe.solarSystems
    }
}
