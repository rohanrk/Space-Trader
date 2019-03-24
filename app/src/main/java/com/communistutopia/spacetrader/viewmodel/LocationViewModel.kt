package com.communistutopia.spacetrader.viewmodel

import android.arch.lifecycle.ViewModel
import com.communistutopia.spacetrader.model.Player

/**
 * Viewmodel that manages changing solar systems
 *
 * @author Rohan Rk <rohanrk@gatech.edu>
 */
class LocationViewModel: ViewModel() {

    lateinit var player: Player

}