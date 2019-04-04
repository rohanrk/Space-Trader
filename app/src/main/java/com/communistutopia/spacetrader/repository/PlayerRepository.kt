package com.communistutopia.spacetrader.repository

import android.arch.lifecycle.MutableLiveData
import com.communistutopia.spacetrader.model.Player
import com.google.firebase.firestore.FirebaseFirestore

object PlayerRepository {
    val player: MutableLiveData<Player> = FirebaseMutableLiveData()
    lateinit var documentId: String

    init {

    }

    class FirebaseMutableLiveData<T>: MutableLiveData<T>() {
        override fun setValue(value: T) {
            val db = FirebaseFirestore.getInstance()
            if (player.value != null) {
                db.collection("player").document(documentId).set(value as Player)
            }
            super.setValue(value)
        }
    }
}


