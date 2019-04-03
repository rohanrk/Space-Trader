package com.communistutopia.spacetrader.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.communistutopia.spacetrader.R
import kotlinx.android.synthetic.main.game_fragment.*


class SelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)


    }

    fun configurePlayer(view: View){
        val intent = Intent(this, ConfigurationActivity::class.java)
        startActivity(intent)
    }

    fun loadScreen(view: View) {
        val intent = Intent(this, LoadingScreenActivity::class.java)
        startActivity(intent)
    }
}
