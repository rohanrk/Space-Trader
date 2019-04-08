package com.communistutopia.spacetrader.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.viewmodel.LocationViewModel
import kotlinx.android.synthetic.main.location_activity.*


class LocationActivity: AppCompatActivity() {

    private lateinit var viewModel: LocationViewModel
    //lateinit var piratePopUp: PopupWindow
    //lateinit var policePopUp: PopupWindow
    lateinit var closePopupBtn: Button


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_activity)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar?.title = "Travel"
        //set back button
        actionbar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)

        curr_solarsystem.text = viewModel.player.value!!.system.name
        curr_planet.text = viewModel.player.value!!.location.name

        val reachablePlanets = viewModel.getAllReachablePlanets()

        val planetsAdapter: ArrayAdapter<LocationViewModel.TravelSpinnerEntry> = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, reachablePlanets.toTypedArray())
        planetsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        planets_spinner.adapter = planetsAdapter



        travel_planet_button.setOnClickListener {
            //Toast.makeText(this, "Traveled!", Toast.LENGTH_SHORT).show()
            val item = planets_spinner.selectedItem as LocationViewModel.TravelSpinnerEntry
            viewModel.travelToPlanet(item.solarSystem, item.planet)
            val event = viewModel.player.value!!.location.market.event.toString()
            val planet = item.planet.name
            val duration = Toast.LENGTH_LONG
            Toast.makeText(
                this,
                "Traveled to $planet, which is experiencing a $event",
                duration
            ).show()
            viewModel.policeEvent.observe(this, Observer<Any> {
                //setup for police popup
                val layoutInflater =
                    this@LocationActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val customView = layoutInflater.inflate(R.layout.police_popup, null)
                closePopupBtn = customView.findViewById<Button>(R.id.button_popup)
                var popupWindow = PopupWindow(
                    customView,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn

                // Slide animation for popup window exit transition
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut
                //display the popup window

                TransitionManager.beginDelayedTransition(findViewById(R.layout.location_activity))
                popupWindow.showAtLocation(
                    root_layout,
                    Gravity.CENTER,
                    0,
                    0
                )

                //close the popup window on button click
                closePopupBtn.setOnClickListener {
                    popupWindow.dismiss()
                    finish()
                }
            })

            viewModel.pirateEvent.observe(this, Observer<Any> {
                //setup for police popup
                val layoutInflater =
                    this@LocationActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val customView = layoutInflater.inflate(R.layout.pirate_popup, null)
                var closePopupBtn = customView.findViewById<Button>(R.id.button_popup)
                var popupWindow = PopupWindow(
                    customView,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn

                // Slide animation for popup window exit transition
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut
                //display the popup window
                popupWindow.showAtLocation(
                    root_layout,
                    Gravity.CENTER,
                    0,
                    0
                )

                //close the popup window on button click
                closePopupBtn.setOnClickListener {
                    popupWindow.dismiss()
                    finish()
                }
            })


        }
    }

    // Make back button work
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}