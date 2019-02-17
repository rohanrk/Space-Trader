package com.communistutopia.spacetrader.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.communistutopia.spacetrader.R

class ConfigurationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configuration_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ConfigurationFragment.newInstance())
                .commitNow()
        }
    }

}
