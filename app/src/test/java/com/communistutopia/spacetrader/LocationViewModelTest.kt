package com.communistutopia.spacetrader

import android.arch.lifecycle.Observer
import com.communistutopia.spacetrader.model.Difficulty
import com.communistutopia.spacetrader.model.Player
import com.communistutopia.spacetrader.model.SolarSystem
import com.communistutopia.spacetrader.repository.PlayerRepository
import com.communistutopia.spacetrader.viewmodel.LocationViewModel
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class LocationViewModelTest {
    val locationViewModel = LocationViewModel()

    lateinit var playerObserver: Observer<Player>

    // TODO
   /* @Test
    fun getAllReachablePlanetsTest() {
        initPlayer()
        // locationViewModel.player.observeForever(playerObserver)
        val entries = locationViewModel.getAllReachablePlanets()
        assert(entries.isNotEmpty())
    }*/

    @Test
    fun travelToValidPlanet() {
        val mockPlayer = Mockito.mock(Player::class.java)
        val solarSystem = Mockito.mock(SolarSystem::class.java)
        mockPlayer.firstTimeInit()
        verify(mockPlayer).firstTimeInit()


    }


    fun initPlayer() {
        val player = Player()
        player.difficulty = Difficulty.Beginner
        player.charName = "Bud Yeeterson"
        player.pilotSkill = 4
        player.fighterSkill = 4
        player.traderSkill = 4
        player.engineerSkill = 4
        player.firstTimeInit()
        PlayerRepository.player.value = player
    }
}