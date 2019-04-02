package com.communistutopia.spacetrader.view

import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.AutoScrollHelper
import android.util.Log
import android.view.View
import com.communistutopia.spacetrader.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.sign_in_button

import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val RC_SIGN_IN: Int = 500
   private lateinit var auth: FirebaseAuth
    private val TAG: String = "Login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.communistutopia.spacetrader.R.layout.activity_login)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("37189289568-pmiu8bvai350gi1k1fmq6pooptod91uu.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        var app = FirebaseApp.initializeApp(this)
        if(app == null) {
            Log.e(TAG, "crap")
        }

        auth = FirebaseAuth.getInstance(app as FirebaseApp)

    }

    override fun onStart() {
        super.onStart()
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null
        val currentUser = auth.currentUser
        updateUI(currentUser)

    }

    private fun updateUI(account: FirebaseUser?){
        if (account == null){

        } else {
            //display google-sign in button
        }

    }
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d( TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG,"signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("signInWithCredential:failure", task.exception)
                    updateUI(null)
                }

                // ...
            }
    }

    fun signIn(view: View){
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w( "Google sign in failed", e)
            }
        }

    }



}
