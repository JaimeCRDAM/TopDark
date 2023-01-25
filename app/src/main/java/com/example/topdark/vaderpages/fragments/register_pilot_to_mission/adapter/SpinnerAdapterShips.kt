package com.example.topdark.vaderpages.fragments.register_pilot_to_mission.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.topdark.R
import models.data_classes.to_present.Ship


class SpinnerAdapterShips(context: Context, private val ships: MutableList<Ship>) :
    ArrayAdapter<Ship>(context, R.layout.fragment_register_pilot_to_mission, ships) {


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
        val row: View = inflater.inflate(R.layout.dropdown_items_ships, parent, false)

        val plate = row.findViewById<TextView>(R.id.DPTVShipPlate)
        val type = row.findViewById<TextView>(R.id.DPTVShipType)
        val avatar = row.findViewById<ImageView>(R.id.DPIVShipAvatar)
        val passengers = row.findViewById<TextView>(R.id.DPTVShipPassengers)
        val cargo = row.findViewById<TextView>(R.id.DPTVShipCargo)


        plate.text = ships[position].plate
        type.text = ships[position].type
        avatar.setImageBitmap(ships[position].avatar)
        passengers.text = cargo.text.toString().plus(ships[position].firstCheck.toString())
        cargo.text = cargo.text.toString().plus(ships[position].secondCheck.toString())




        return row
    }
}