package com.jpvargas.testconf.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.jpvargas.testconf.model.Conference
import com.jpvargas.testconf.model.Speaker

const val CONFERENCES_COLLECTION_NAME = "conferences";
const val SPEAKERS_COLLECION_NAME = "speakers"

class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = settings;
    }

    fun getSpeakers(callback: Callback<List<Speaker>>) {
        firebaseFirestore.collection(SPEAKERS_COLLECION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { results ->
                for (doc in results) {
                    val list = results.toObjects(Speaker::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getSchedules(callback: Callback<List<Conference>>) {
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .orderBy("tag")
            .get()
            .addOnSuccessListener { results ->
                for (doc in results) {
                    val list = results.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
            .addOnFailureListener { exception -> Log.e("FIRESTORESERVICE", exception.toString())  }

    }
}