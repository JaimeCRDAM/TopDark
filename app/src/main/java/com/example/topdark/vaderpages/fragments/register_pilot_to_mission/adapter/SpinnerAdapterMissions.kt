package com.example.topdark.vaderpages.fragments.register_pilot_to_mission.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.topdark.R
import models.data_classes.to_present.Mission
import models.data_classes.to_present.Ship


class SpinnerAdapterMissions(
    context: Context,
    var mission: MutableList<Mission>,
    var ships: MutableList<Ship>
) :
    ArrayAdapter<Mission>(context, R.layout.fragment_register_pilot_to_mission, mission) {

    var auxiliarMission = mission.toList()
    val hashmap = hashMapOf<String, String>()

    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        return getCustomView(position, convertView, parent)
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row: View = inflater.inflate(R.layout.dropdown_items_missions, parent, false)

        val type = row.findViewById<TextView>(R.id.DPTVMissionType)
        val amount = row.findViewById<TextView>(R.id.DPTVShipAmount)
        val passengers = row.findViewById<TextView>(R.id.DPTVShipPassengers)
        val cargo = row.findViewById<TextView>(R.id.DPTVShipCargo)


        type.text = mission[position].missionType
        amount.text = amount.text.toString().plus(mission[position].amount.toString())
        passengers.text = passengers.text.toString().plus(mission[position].firstCheck.toString())
        cargo.text = cargo.text.toString().plus(mission[position].secondCheck.toString())

        return row
    }

    fun prepare(){

    }

}