package com.communistutopia.spacetrader.view

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
         * go to the config screen.
         */
        FirebaseApp.initializeApp(this)
        //SelectionActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
