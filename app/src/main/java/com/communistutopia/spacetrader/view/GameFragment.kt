package com.communistutopia.spacetrader.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.model.Player
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        // Start audio
        viewModel.goToSpace(context)
        viewModel.player = activity!!.intent.getParcelableExtra("player")
        market_button.setOnClickListener {
            val intent = Intent(context!!, MarketplaceActivity::class.java)
            intent.putExtra("market", viewModel.player.location.market)
            intent.putExtra("player", viewModel.player)
            startActivity(intent)
        }
    }
}
