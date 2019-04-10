package com.communistutopia.spacetrader.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.databinding.GameFragmentBinding
import com.communistutopia.spacetrader.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.game_fragment.*

/**
 * This displays the main game screen
 */
class GameFragment : Fragment() {
    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.viewModel = viewModel
        // Start audio
        viewModel.goToSpace(context)

        // planet_name.text = "Planet: %s".format(viewModel.player.value?.location?.name)

        market_button.setOnClickListener {
            val intent = Intent(context!!, MarketplaceActivity::class.java)
            startActivity(intent)
        }

        travel_button.setOnClickListener {
            val intent = Intent(context!!, LocationActivity::class.java)
            startActivity(intent)
        }
    }
}
