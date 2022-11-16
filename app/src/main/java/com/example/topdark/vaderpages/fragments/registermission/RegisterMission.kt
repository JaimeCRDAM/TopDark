package com.example.topdark.vaderpages.fragments.registermission

import Models.dataclasses.missions.MissionsEnum
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.topdark.databinding.FragmentRegisterMissionBinding
import com.example.topdark.ui.login.LoginViewModel
import com.example.topdark.ui.login.LoginViewModelFactory
import com.example.topdark.vaderpages.Activity_Vader

class RegisterMission : Fragment() {
    private var _binding: FragmentRegisterMissionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var item: String
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterMissionBinding.inflate(inflater, container, false)

        prepareViewModel()

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

        binding.BTNRegisterMissionMenu.setOnClickListener {
            val klass = MissionsEnum.valueOf(item)
            if (item == MissionsEnum.Flight.missionClass){
                val klassInstance = klass.dataClass.newInstance(
                    Integer.parseInt(binding.TBFlight.text.toString())
                )
                val klassType = klassInstance.javaClass

            }
            if (item == MissionsEnum.Combat.missionClass){
                val klassInstance = klass.dataClass.newInstance(
                    Integer.parseInt(binding.TBEnemies.text.toString()),
                    binding.RBBombardmentCargo.isChecked,
                    binding.RBBombardmentPassengers.isChecked
                )
                val klassType = klassInstance.javaClass

            }
            if (item == MissionsEnum.Bombardment.missionClass){
                val klassInstance = klass.dataClass.newInstance(
                    Integer.parseInt(binding.TBEnemies.text.toString()),
                    binding.RBBombardmentCargo.isChecked,
                    binding.RBBombardmentPassengers.isChecked
                )
                val klassType = klassInstance.javaClass

            }

        }

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
        item = mission
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

    fun prepareViewModel(){
        registerViewModel = ViewModelProvider(this, RegisterViewModelFactory())
            .get(RegisterViewModel::class.java)

        registerViewModel.registerResult.observe(viewLifecycleOwner, Observer {
            val registerResult = it ?: return@Observer

            if (registerResult.error != null) {
                Toast.makeText(requireContext(), "hola", Toast.LENGTH_SHORT).show()
            }
            if (registerResult.success != null) {

            }

            //Complete and destroy login activity once successful
            //finish()
        })
    }
}