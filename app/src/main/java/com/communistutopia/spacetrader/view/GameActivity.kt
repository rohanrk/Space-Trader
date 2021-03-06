package com.communistutopia.spacetrader.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.communistutopia.spacetrader.R

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GameFragment.newInstance())
                .commitNow()
        }

    }


}
