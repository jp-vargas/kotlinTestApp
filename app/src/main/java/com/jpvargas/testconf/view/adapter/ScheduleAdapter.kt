package com.jpvargas.testconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jpvargas.testconf.R
import com.jpvargas.testconf.model.Conference
import java.text.SimpleDateFormat
import java.util.*

class ScheduleAdapter(val scheduleListener: ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    private val listConference = ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))
    }

    override fun getItemCount(): Int {
        return listConference.size
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConference[position]

        holder.tvConferenceName.text = conference.title
        holder.tvConferenceSpeaker.text = conference.speaker
        holder.tvConferenceTag.text = conference.tag

        val simpleDateformat = SimpleDateFormat("HH:mm")
        val simpleDateformatAMPM = SimpleDateFormat("a")

        val calendar = Calendar.getInstance()
        calendar.time = conference.dateTime
        val hourFormat = simpleDateformat.format(conference.dateTime)

        holder.tvConferenceHour.text = hourFormat
        holder.tvConferenceAMPM.text = simpleDateformatAMPM.format(conference.dateTime).toUpperCase()

        holder.itemView.setOnClickListener { scheduleListener.onConferenceClicked(conference, position) }
    }

    fun updateData(data: List<Conference>) {
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvConferenceName : TextView = itemView.findViewById(R.id.tvItemScheduleConferenceName)
        val tvConferenceSpeaker : TextView = itemView.findViewById(R.id.tvItemScheduleConferenceSpeaker)
        val tvConferenceTag : TextView = itemView.findViewById(R.id.tvItemScheduleTag)
        val tvConferenceHour : TextView = itemView.findViewById(R.id.tvItemScheduleHour)
        val tvConferenceAMPM : TextView = itemView.findViewById(R.id.tvItemScheduleAMPM)
    }
}