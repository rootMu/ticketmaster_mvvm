package com.example.matthew.ticketmaster_mvvm

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthew.ticketmaster_mvvm.model.Event
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by Matthew on 10/06/2018.
 */

class ListAdapter (val eventData: ArrayList<Event>, val clickListener: (Event) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return PartViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Populate ViewHolder with data that corresponds to the position in the list
        // which we are told to load
        (holder as PartViewHolder).bind(eventData[position], clickListener)
    }

    override fun getItemCount() = eventData.size

    class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event, clickListener: (Event) -> Unit) {
            itemView.tvTitle.text = event.name
            itemView.setOnClickListener { clickListener(event) }
        }
    }
}