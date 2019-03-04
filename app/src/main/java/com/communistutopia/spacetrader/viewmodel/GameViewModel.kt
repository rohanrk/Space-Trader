package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.SolarSystem
import com.communistutopia.spacetrader.model.Universe

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
}
