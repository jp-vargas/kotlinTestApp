package com.jpvargas.testconf.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide

import com.jpvargas.testconf.R
import com.jpvargas.testconf.model.Conference
import com.jpvargas.testconf.model.Speaker
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*
import java.net.URI
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class SpeakersDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarSpeaker.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarSpeaker.setTitleTextColor(Color.WHITE)
        toolbarSpeaker.setNavigationOnClickListener {
            dismiss()
        }

        val speaker = arguments?.getSerializable("speaker") as Speaker
        toolbarSpeaker.title =  speaker.name

        Glide
            .with(view)
            .load(speaker.image)
            .into(ivExpositorImage)

        ivExpositorSocial.setOnClickListener {
            val browserIntent : Intent = Intent(Intent.ACTION_VIEW, Uri.parse(speaker.twitter))
            startActivity(browserIntent)
        }

        tvExpositorName.text = speaker.name
        tvExpositorJob.text = speaker.jobTitle
        tvExpositorProject.text = speaker.workplace
        tvExpositorDescription.text = speaker.biography

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}
