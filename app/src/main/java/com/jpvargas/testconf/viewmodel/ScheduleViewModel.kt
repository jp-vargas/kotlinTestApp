package com.jpvargas.testconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jpvargas.testconf.model.Conference
import com.jpvargas.testconf.network.Callback
import com.jpvargas.testconf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel : ViewModel(){
    private val firestoreService = FirestoreService()

    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun processFinished() {
        isLoading.value = true
    }

    fun getScheduleFromFirebase() {
        firestoreService.getSchedules(object: Callback<List<Conference>> {
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailure(exception: Exception) {
                processFinished()
            }
        })
    }

    fun refresh() {
        getScheduleFromFirebase()
    }
}