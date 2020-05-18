package com.jpvargas.testconf.view.adapter

import com.jpvargas.testconf.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker, position: Int)
}