package com.example.topdark.vaderpages.fragments.registermission

import Models.dataclasses.missions.GeneralDataClass
import Models.dataclasses.missions.MissionsEnum
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.topdark.databinding.FragmentRegisterMissionBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterMission : Fragment() {
    private var _binding: FragmentRegisterMissionBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var item: String
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var currentGroup: Group

    @RequiresApi(Build.VERSION_CODES.O)
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
                this@RegisterMission.item = parent.selectedItem.toString()
                if (this@RegisterMission::currentGroup.isInitialized){
                    currentGroup.visibility = View.GONE
                    resetCheckBoxes()
                }
                currentGroup = requireView().findViewById(MissionsEnum.valueOf(item).group)
                binding.TBAmount.setHint(MissionsEnum.valueOf(item).hint)
                currentGroup.visibility = View.VISIBLE
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        binding.BTNRegisterMissionMenu.setOnClickListener {
            val klass = MissionsEnum.valueOf(item)
            val generalDataClass = GeneralDataClass(
                Integer.parseInt(binding.TBAmount.text.toString()),
                binding.RBBombardmentCargo.isChecked,
                binding.RBBombardmentPassengers.isChecked
            )

            CoroutineScope(Dispatchers.IO).launch{
                registerViewModel.requestResult(generalDataClass, klass.missionClass)
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

    fun resetCheckBoxes(){
        binding.RBBombardmentCargo.isChecked = false
        binding.RBBombardmentPassengers.isChecked = false
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