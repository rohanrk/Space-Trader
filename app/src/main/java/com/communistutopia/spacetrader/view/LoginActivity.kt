package com.communistutopia.spacetrader.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.communistutopia.spacetrader.R
import com.communistutopia.spacetrader.model.Player
import com.communistutopia.spacetrader.repository.PlayerRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
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
            .requestIdToken(getString(R.string.google_auth_token))
            .requestEmail()
            .build()

        auth = FirebaseAuth.getInstance()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        sign_in_button.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }

    /**
     * Check for existing sign in
     */
    override fun onStart() {
        super.onStart()
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null
        val currentUser = auth.currentUser
        if (currentUser != null) {
            authSuccess(currentUser)
        }


    }

    /**
     * This function will be called upon successfully authenticating
     * or if Firebase found a stored session. Will determine if there
     * is a player object and then which screen to send the user to.
     */
    private fun authSuccess(user: FirebaseUser) {
        // Query for player object with associated uid
        val db = FirebaseFirestore.getInstance()
        val games = db.collection("player").whereEqualTo("uid", user.uid)
        games.get().addOnSuccessListener { document ->
            if (document != null) {
                if (document.documents.size == 0) {
                    // no previous game
                    Log.d(TAG, "No games found. Generating new one.")
                    val intent = Intent(this, ConfigurationActivity::class.java)
                    startActivity(intent)
                } else {
                    val playerDocument = document.documents.first()
                    val player = playerDocument.toObject(Player::class.java)
                    val documentId = playerDocument.id
                    PlayerRepository.documentId = documentId
                    PlayerRepository.player.value = player
                    val intent = Intent(this, GameActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Log.d(TAG, "ERROR: No games found.")
            }
        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
    }

    /**
     * Called from the result of the Google Login. Matches the Google User
     * to the Firebase user.
     */
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d( TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG,"signInWithCredential:success")
                    val user = auth.currentUser
                    authSuccess(user!!)
                } else {
                    // If sign in fails, display a message to the user.
                    // TODO: Make this a toast or something
                    Log.w("signInWithCredential:failure", task.exception)
                }
            }
    }

    /**
     * Gets the result from the Google Login
     */
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
                // TODO: A toast or something
                Log.w( "Google sign in failed", e)
            }
        }
    }
}
