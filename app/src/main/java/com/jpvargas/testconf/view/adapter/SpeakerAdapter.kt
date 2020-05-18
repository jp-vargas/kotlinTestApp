package com.jpvargas.testconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jpvargas.testconf.R
import com.jpvargas.testconf.model.Speaker

class SpeakerAdapter(val speakerListener: SpeakerListener): RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {
    private val speakersList = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent, false))
    }

    override fun getItemCount(): Int = speakersList.size

    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {
        val speaker = speakersList[position]
        holder.tvSpeakerName.text = speaker.name
        holder.tvSpeakerJob.text = speaker.jobTitle
        Glide
            .with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.ivSpeakerImage)

        holder.itemView.setOnClickListener { speakerListener.onSpeakerClicked(speaker, position) }
    }

    fun updateData(data: List<Speaker>) {
        speakersList.clear()
        speakersList.addAll(data)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSpeakerName : TextView = itemView.findViewById(R.id.tvSpeakerName)
        val ivSpeakerImage : ImageView = itemView.findViewById(R.id.ivSpeakerImage)
        val tvSpeakerJob : TextView = itemView.findViewById(R.id.tvSpeakerJob)
    }
}