package com.jpvargas.testconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jpvargas.testconf.model.Speaker
import com.jpvargas.testconf.network.Callback
import com.jpvargas.testconf.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel: ViewModel() {
    private val firestoreService = FirestoreService()

    var listSpeakers: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun processFinished() {
        isLoading.value = true
    }

    fun getSpeakersFromFirebase() {
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result)
                processFinished()
            }

            override fun onFailure(exception: Exception) {
                processFinished()
            }
        })
    }

    fun refresh() {
        getSpeakersFromFirebase()
    }
}