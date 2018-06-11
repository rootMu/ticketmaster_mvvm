package com.example.matthew.ticketmaster_mvvm

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthew.ticketmaster_mvvm.model.event.Event
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by Matthew on 10/06/2018.
 */

class ListAdapter (val eventData: ArrayList<Event>, val clickListener: (Event) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Populate ViewHolder with data that corresponds to the position in the list
        // which we are told to load
        (holder as EventViewHolder).bind(eventData[position], clickListener)
    }

    override fun getItemCount() = eventData.size

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(event: Event, clickListener: (Event) -> Unit) {
            itemView.tvTitle.text = event.name
            event.venues?.venues?.let{
                itemView.tvVenue.text = it.first().name
            }

            //TODO: handle date formatting and error handling when date to be decided or announced
            itemView.tvDate.text = event.dates.date.date

            //update to use all images with a custom view pager
            event.images?.first()?.let{
                Picasso.get()
                        .load(it.url)
                        .centerCrop()
                        .resize(150,150)
                        .into(itemView.ivMainImage)
            }

            setFavourite(event, clickListener)
            setShowHide(event)
        }

        private fun setShowHide(event: Event) {

            event.collapsed?.let{
                itemView.showHide?.isActivated = it
            }

            showHideEvent(event.collapsed)
            //set onclicklistener to toggle visibility
            itemView.showHide.setOnClickListener({
                itemView.showHide.isActivated = !itemView.showHide.isActivated
                event.collapsed = itemView.showHide.isActivated
                showHideEvent(event.collapsed)
            })
        }

        private fun showHideEvent(collapsed: Boolean){
            when(collapsed){
                true -> itemView.eventInfo.visibility = View.GONE
                false -> itemView.eventInfo.visibility = View.VISIBLE
            }
        }

        private fun setFavourite(event: Event, clickListener: (Event) -> Unit) {

            event.favourite?.let {
                itemView.favourite?.isActivated = it
            }
            //set onclicklistener to toggle favourite
            itemView.favourite.setOnClickListener({
                clickListener(event)
            })
        }
    }
}