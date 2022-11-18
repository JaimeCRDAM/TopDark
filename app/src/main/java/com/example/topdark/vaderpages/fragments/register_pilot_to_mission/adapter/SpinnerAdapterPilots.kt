package com.example.topdark.vaderpages.fragments.register_pilot_to_mission.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.topdark.R
import models.data_classes.to_present.Pilot


class SpinnerAdapterPilots(context: Context, private val pilots: MutableList<Pilot>) :
    ArrayAdapter<Pilot>(context, R.layout.fragment_register_pilot_to_mission, pilots) {


    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row: View = inflater.inflate(R.layout.dropdown_items_pilots, parent, false)

        val name = row.findViewById<TextView>(R.id.DPTVShipCargo)
        val avatar = row.findViewById<ImageView>(R.id.DPIVShipAvatar)
        val experience = row.findViewById<TextView>(R.id.DPTVShipPassengers)

        //Set state abbreviation and state flag
        name.text =  name.text.toString().plus(pilots[position].name)
        avatar.setImageBitmap(pilots[position].avatar)
        experience.text = experience.text.toString().plus(pilots[position].name)
        return row
    }
}