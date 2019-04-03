package com.communistutopia.spacetrader.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.communistutopia.spacetrader.R
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** Go to the configuration activity
         * TODO: In the future it needs to decide which screen to go on based on state. Is there a user? Then don't
         * go to the config screen.
         */
        FirebaseApp.initializeApp(this)
        val intent = Intent(this, ConfigurationActivity::class.java)
        startActivity(intent)
    }
}
