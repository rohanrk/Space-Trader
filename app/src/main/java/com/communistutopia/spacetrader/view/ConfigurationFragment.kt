package com.communistutopia.spacetrader.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.model.Difficulty
import com.communistutopia.spacetrader.viewmodel.ConfigurationViewModel
import kotlinx.android.synthetic.main.configuration_fragment.player_name
import kotlinx.android.synthetic.main.configuration_fragment.difficulty_spinner
import kotlinx.android.synthetic.main.configuration_fragment.pilot_points
import kotlinx.android.synthetic.main.configuration_fragment.trader_points
import kotlinx.android.synthetic.main.configuration_fragment.fighter_points
import kotlinx.android.synthetic.main.configuration_fragment.engineer_points
import kotlinx.android.synthetic.main.configuration_fragment.finish

class ConfigurationFragment : Fragment() {

    companion object {
        fun newInstance() = ConfigurationFragment()
    }

    private lateinit var viewModel: ConfigurationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.configuration_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel::class.java)

        // TODO: Use the ViewModel
    }

    fun finishPressed(view: View) {

        viewModel.updatePlayerFromView(difficulty_spinner.selectedItem as Difficulty, player_name.text as String,
            pilot_points.text as Int, fighter_points.text as Int, trader_points.text as Int, engineer_points as Int)
    }

}
