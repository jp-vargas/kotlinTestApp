package com.jpvargas.testconf.view.adapter

import com.jpvargas.testconf.model.Conference

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}