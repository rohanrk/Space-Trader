package com.communistutopia.spacetrader.repository

import android.arch.lifecycle.MutableLiveData
import com.communistutopia.spacetrader.model.Player

object PlayerRepository {
    val player: MutableLiveData<Player> = MutableLiveData()

    init {
        player.value = Player()
    }
}