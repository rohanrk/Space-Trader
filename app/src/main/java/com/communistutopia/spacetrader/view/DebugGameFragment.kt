package com.communistutopia.spacetrader.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.adapter.SolarSystemAdapter
import com.communistutopia.spacetrader.model.SolarSystem
import com.communistutopia.spacetrader.viewmodel.DebugGameViewModel
import kotlinx.android.synthetic.main.debug_game_fragment.*

class DebugGameFragment : Fragment() {
    companion object {
        fun newInstance() = DebugGameFragment()
    }

    private lateinit var viewModel: DebugGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.debug_game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DebugGameViewModel::class.java)
        // TODO: Use the ViewModel
        val solarSystems: List<SolarSystem> = ArrayList<SolarSystem>(viewModel.solarSystems)
        val adapter = SolarSystemAdapter(context!!, solarSystems)
        solar_systems_list.adapter = adapter
    }

}
