package com.communistutopia.spacetrader.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.viewmodel.LocationViewModel
import kotlinx.android.synthetic.main.location_activity.*

class LocationActivity: AppCompatActivity() {

    private lateinit var viewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_activity)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Travel"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)

        curr_solarsystem.text = viewModel.player.value!!.system.name
        curr_planet.text = viewModel.player.value!!.location.name

        val reachablePlanets = viewModel.getAllReachablePlanets()

        val planetsAdapter: ArrayAdapter<LocationViewModel.TravelSpinnerEntry> = ArrayAdapter(this, android.R.layout.simple_spinner_item, reachablePlanets.toTypedArray())
        planetsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        planets_spinner.adapter = planetsAdapter


        travel_planet_button.setOnClickListener {
            //Toast.makeText(this, "Traveled!", Toast.LENGTH_SHORT).show()
            var item = planets_spinner.selectedItem as LocationViewModel.TravelSpinnerEntry
            viewModel.travelToPlanet(item.solarSystem, item.planet)
            val event = viewModel.player.value!!.location.market.event.toString()
            val planet = item.planet.name
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(
                this,
                "Traveled to $planet, which is experiencing a $event",
                duration
            ).show()
            finish()
        }
    }

    // Make back button work
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}