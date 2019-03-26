package com.communistutopia.spacetrader.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.model.Difficulty
import com.communistutopia.spacetrader.viewmodel.ConfigurationViewModel
import kotlinx.android.synthetic.main.configuration_fragment.*

/**
 * Fragment that shows all elements of player creation
 *
 * @author Rohan Rk <rohanrk@gatech.edu>
 */
class ConfigurationFragment : Fragment(), View.OnClickListener {

    private lateinit var button: Button
    private lateinit var difficultySpinner: Spinner

    companion object {
        fun newInstance() = ConfigurationFragment()
    }

    private lateinit var viewModel: ConfigurationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var root = inflater.inflate(R.layout.configuration_fragment, container, false)

        button = root.findViewById(R.id.finish)
        difficultySpinner = root.findViewById(R.id.difficulty_spinner)

        var difficultyAdapter: ArrayAdapter<Difficulty> = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, Difficulty.values())
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        difficultySpinner.adapter = difficultyAdapter

        button.setOnClickListener(this)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel::class.java)

        // TODO: Use the ViewModel
    }

    override fun onClick(p0: View?) {
        if (viewModel.updatePlayerFromView(difficulty_spinner.selectedItem as Difficulty, player_name.text.toString(), pilot_points.text.toString().toInt(),
            fighter_points.text.toString().toInt(), trader_points.text.toString().toInt(), engineer_points.text.toString().toInt())) {
            // If the player is valid, go to the GameActivity (which at the moment just has a debug fragment
            val intent = Intent(context!!, GameActivity::class.java)
            intent.putExtra("player", viewModel.player)
            startActivity(intent)

        } else {
            var err: String = "Points must be nonnegative integers that add up to 16"
            Toast.makeText(this.activity, "Player not created. " + err, Toast.LENGTH_LONG).show()
        }

    }

}
