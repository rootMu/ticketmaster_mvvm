package com.example.matthew.ticketmaster_mvvm

import android.content.Context
import android.provider.MediaStore
import android.support.v7.widget.DrawableUtils
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.matthew.ticketmaster_mvvm.R.id.image
import com.example.matthew.ticketmaster_mvvm.R.id.tvInfo
import com.example.matthew.ticketmaster_mvvm.model.event.Event
import com.example.matthew.ticketmaster_mvvm.model.image.Image
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.AttributedCharacterIterator

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
            setShowHide()
        }

        private fun setShowHide() {
            //reset values for recycler items
            itemView.showHide.isActivated = false
            itemView.eventInfo.visibility = View.VISIBLE
            //set onclicklistener to toggle visibility
            itemView.showHide.setOnClickListener({
                itemView.showHide.isActivated = !itemView.showHide.isActivated
                when(itemView.showHide.isActivated ){
                    true -> itemView.eventInfo.visibility = View.GONE
                    false -> itemView.eventInfo.visibility = View.VISIBLE
                }
            })
        }

        private fun setFavourite(event: Event, clickListener: (Event) -> Unit) {
            //TODO: read from room
            //reset values for recycler items
            itemView.favourite.isActivated = false
            //set onclicklistener to toggle favourite
            itemView.favourite.setOnClickListener({
                clickListener(event)
                itemView.favourite.isActivated = !itemView.favourite.isActivated
            })
        }
    }
}