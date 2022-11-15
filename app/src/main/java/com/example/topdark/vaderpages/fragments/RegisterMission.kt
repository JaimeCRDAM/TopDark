package com.example.topdark.vaderpages.fragments

import Models.dataclasses.missions.MissionsEnum
import Models.dataclasses.ship.ShipsEnum
import android.graphics.BitmapFactory
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.topdark.R
import com.example.topdark.databinding.FragmentRegisterMissionBinding

class RegisterMission : Fragment() {
    private var _binding: FragmentRegisterMissionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterMissionBinding.inflate(inflater, container, false)
        val shipArray = MissionsEnum.values().map {it.missionClass}
        val adapter = ArrayAdapter(
            this.requireContext(), android.R.layout.simple_spinner_dropdown_item, shipArray
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.SpinnerMission.adapter = adapter

        binding.SpinnerMission.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val item = parent.selectedItem.toString()
                hideAndSeek(item)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun hideAndSeek(mission: String){
        if (mission == MissionsEnum.Flight.missionClass){
            binding.TBFlight.visibility = View.VISIBLE
        } else {
            binding.TBFlight.visibility = View.GONE
        }
        if (mission == MissionsEnum.Combat.missionClass){
            binding.TBEnemies.visibility = View.VISIBLE
        } else {
            binding.TBEnemies.visibility = View.GONE
        }
        if (mission == MissionsEnum.Bombardment.missionClass){
            binding.TBTargets.visibility = View.VISIBLE
            binding.RBBombardmentCargo.visibility = View.VISIBLE
            binding.RBBombardmentPassengers.visibility = View.VISIBLE
        } else{
            binding.TBTargets.visibility = View.GONE
            binding.RBBombardmentCargo.visibility = View.GONE
            binding.RBBombardmentPassengers.visibility = View.GONE
        }
    }
}