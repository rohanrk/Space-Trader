package com.communistutopia.spacetrader.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.model.SolarSystem
import com.communistutopia.spacetrader.viewmodel.LocationViewModel
import kotlinx.android.synthetic.main.location_activity.*

class LocationActivity: AppCompatActivity() {

    private lateinit var viewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_activity)

        viewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        viewModel.player = intent.getParcelableExtra("player")
        viewModel.player.location = intent.getParcelableExtra("location")
        viewModel.player.system = intent.getParcelableExtra("system")

        curr_solarsystem.text = viewModel.player.system.name
        curr_planet.text = viewModel.player.location.name

        val reachablePlanets = viewModel.getAllReachablePlanets()

        val planetsAdapter: ArrayAdapter<LocationViewModel.TravelSpinnerEntry> = ArrayAdapter(this, android.R.layout.simple_spinner_item, reachablePlanets.toTypedArray())
        planetsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        planets_spinner.adapter = planetsAdapter
    }
}